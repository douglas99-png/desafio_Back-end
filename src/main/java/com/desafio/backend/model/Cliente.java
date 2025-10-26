package com.desafio.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCliente")
    private Long id;

    @Column(name = "Nome", nullable = false, length = 120)
    private String nome;

    @Column(name = "CPF", nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "CEP", nullable = false, length = 8)
    private String cep;

    @Column(name = "Logradouro", nullable = false, length = 120)
    private String logradouro;

    @Column(name = "Complemento", length = 100)
    private String complemento;

    @Column(name = "Bairro", nullable = false, length = 80)
    private String bairro;

    @Column(name = "Cidade", nullable = false, length = 80)
    private String cidade;

    @Column(name = "UF", nullable = false, length = 2)
    private String uf;

    @Column(name = "DataCadastro")
    private LocalDateTime dataCadastro;

    @Column(name = "DataAtualizacao")
    private LocalDateTime dataAtualizacao;

    // Relacionamentos
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Telefone> telefones = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Email> emails = new ArrayList<>();

    // Métodos auxiliares
    public void addTelefone(Telefone telefone) {
        telefone.setCliente(this);
        this.telefones.add(telefone);
    }

    public void addEmail(Email email) {
        email.setCliente(this);
        this.emails.add(email);
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }
}
