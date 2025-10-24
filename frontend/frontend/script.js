function getUsername() {
  return localStorage.getItem("username");
}

function getToken() {
  return localStorage.getItem("authToken");
}

if (!getToken() || !getUsername()) {
  alert("Faça login para continuar!");
  window.location.href = "../login/login.html";
}

const username = getUsername();
const token = getToken();
const isAdmin = username === "admin";

const infoUser = document.createElement("div");
infoUser.textContent = `Usuário logado: ${username}`;
infoUser.style.textAlign = "center";
infoUser.style.fontWeight = "bold";
infoUser.style.margin = "10px 0";
document.body.prepend(infoUser);

const form = document.getElementById("formCadastro");
const tabela = document.getElementById("tabelaClientes");


if (!isAdmin) {
  form.style.display = "none";
  const aviso = document.createElement("p");
  aviso.textContent = "Usuário padrão: acesso somente de leitura (consulta).";
  aviso.style.textAlign = "center";
  aviso.style.fontWeight = "bold";
  aviso.style.color = "red";
  document.body.insertBefore(aviso, tabela);
}


const cpfInput = document.getElementById("cpf");
if (cpfInput) {
  cpfInput.addEventListener("input", (e) => {
    let valor = e.target.value.replace(/\D/g, "");
    if (valor.length > 11) valor = valor.slice(0, 11);
    valor = valor.replace(/(\d{3})(\d)/, "$1.$2");
    valor = valor.replace(/(\d{3})(\d)/, "$1.$2");
    valor = valor.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
    e.target.value = valor;
  });
}


const cepInput = document.getElementById("cep");
if (cepInput) {
  cepInput.addEventListener("input", (e) => {
    let valor = e.target.value.replace(/\D/g, "");
    if (valor.length > 8) valor = valor.slice(0, 8);
    valor = valor.replace(/(\d{5})(\d)/, "$1-$2");
    e.target.value = valor;
  });
}

const telefoneInput = document.getElementById("telefone");
if (telefoneInput) {
  telefoneInput.addEventListener("input", (e) => {
    let valor = e.target.value.replace(/\D/g, "");
    if (valor.length > 11) valor = valor.slice(0, 11);

    if (valor.length <= 10) {
      valor = valor.replace(/(\d{2})(\d{4})(\d{0,4})/, "($1) $2-$3");
    } else {
      valor = valor.replace(/(\d{2})(\d{5})(\d{0,4})/, "($1) $2-$3");
    }

    e.target.value = valor;
  });
}


async function carregarClientes() {
  try {
    const res = await fetch("http://localhost:8080/clientes", {
      headers: { "Authorization": "Basic " + token },
    });

    if (!res.ok) throw new Error("Erro ao buscar clientes");

    const clientes = await res.json();
    const tbody = document.getElementById("tbody");
    tbody.innerHTML = "";

    clientes.forEach(c => {
      const tr = document.createElement("tr");
      tr.innerHTML = `
        <td>${c.nome}</td>
        <td>${c.cpf}</td>
        <td>${c.endereco?.cidade || c.cidade}</td>
        <td>${c.endereco?.uf || c.uf}</td>
        <td>${c.emails?.[0]?.email || "—"}</td>
      `;
      tbody.appendChild(tr);
    });
  } catch (err) {
    console.error(err);
    alert("Erro ao carregar clientes.");
  }
}


async function cadastrarCliente() {
  if (!isAdmin) {
    alert("Usuário padrão não pode cadastrar!");
    return;
  }

  const nome = document.getElementById("nome").value.trim();
  const cpf = document.getElementById("cpf").value.replace(/\D/g, "");
  const telefone = document.getElementById("telefone").value.replace(/\D/g, "");
  const cep = document.getElementById("cep").value.replace(/\D/g, "");
  const logradouro = document.getElementById("logradouro").value.trim();
  const bairro = document.getElementById("bairro").value.trim();
  const cidade = document.getElementById("cidade").value.trim();
  const uf = document.getElementById("uf").value.trim();
  const email = document.getElementById("email").value.trim();

  if (!nome || !cpf || !telefone || !cep || !logradouro || !bairro || !cidade || !uf || !email) {
    alert("Preencha todos os campos!");
    return;
  }

  const cliente = {
    nome,
    cpf,
    cep,
    logradouro,
    bairro,
    cidade,
    uf,
    dataCadastro: new Date().toISOString(),
    dataAtualizacao: new Date().toISOString(),
    telefones: [{ numero: telefone, tipo: 1 }],
    emails: [{ email }],
  };

  try {
    const res = await fetch("http://localhost:8080/clientes", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Basic " + token,
      },
      body: JSON.stringify(cliente),
    });

    if (!res.ok) throw new Error(await res.text());

    alert("Cliente cadastrado com sucesso!");
    form.reset();
    carregarClientes();
  } catch (err) {
    alert("Erro ao cadastrar: " + err.message);
  }
}

async function buscarCEP() {
  const cep = document.getElementById("cep").value.replace(/\D/g, "");
  if (cep.length !== 8) {
    alert("CEP inválido!");
    return;
  }

  try {
    const res = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
    const data = await res.json();

    if (data.erro) {
      alert("CEP não encontrado!");
      return;
    }

    document.getElementById("logradouro").value = data.logradouro;
    document.getElementById("bairro").value = data.bairro;
    document.getElementById("cidade").value = data.localidade;
    document.getElementById("uf").value = data.uf;
  } catch {
    alert("Erro ao buscar CEP!");
  }
}


window.onload = carregarClientes;
