package com.desafio.backend.dto;

import com.desafio.backend.model.Cliente;
import com.desafio.backend.util.MaskUtils;

public class ClienteDTO {
    public Long id;
    public String nome;
    public String cpf;
    public String cep;
    public String logradouro;
    public String complemento;
    public String bairro;
    public String cidade;
    public String uf;
    public String dataCadastro;
    public String dataAtualizacao;

    public static ClienteDTO fromEntity(Cliente c) {
        ClienteDTO dto = new ClienteDTO();
        dto.id = c.getId();
        dto.nome = c.getNome();
        dto.cpf = MaskUtils.CPF(c.getCpf());
        dto.cep = MaskUtils.maskCEP(c.getCep());
        dto.logradouro = c.getLogradouro();
        dto.complemento = c.getComplemento();
        dto.bairro = c.getBairro();
        dto.cidade = c.getCidade();
        dto.uf = c.getUf();
        dto.dataCadastro = c.getDataCadastro() != null ? c.getDataCadastro().toString() : null;
        dto.dataAtualizacao = c.getDataAtualizacao() != null ? c.getDataAtualizacao().toString() : null;
        return dto;
    }
}
