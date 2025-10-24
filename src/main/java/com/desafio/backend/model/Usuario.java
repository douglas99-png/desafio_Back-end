package com.desafio.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    private Long id;

    @Column(name = "Login", nullable = false, unique = true)
    private String login;

    @Column(name = "Senha", nullable = false)
    private String senha;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
