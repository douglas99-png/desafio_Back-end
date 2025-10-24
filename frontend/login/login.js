document.getElementById("loginForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const login = document.getElementById("login").value.trim();
  const senha = document.getElementById("senha").value.trim();

  if (!login || !senha) {
    alert("Informe login e senha!");
    return;
  }

  const token = btoa(`${login}:${senha}`);

  try {
    const res = await fetch("http://localhost:8080/clientes", {
      method: "GET",
      headers: {
        "Authorization": "Basic " + token,
      },
    });

    if (res.status === 401) {
      throw new Error("Usuário ou senha inválidos!");
    }

    localStorage.setItem("authToken", token);
    localStorage.setItem("username", login.toLowerCase());

    window.location.href = "../frontend/index.html";
  } catch (err) {
    alert(err.message || "Erro ao conectar com o servidor.");
  }
});
