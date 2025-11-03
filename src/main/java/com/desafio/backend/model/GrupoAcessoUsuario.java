package com.desafio.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GrupoAcesso_Usuario")
public class GrupoAcessoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdGrupoAcessoUsuario")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IdUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "IdGrupoAcesso", nullable = false)
    private GrupoAcesso grupoAcesso;

    public GrupoAcessoUsuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public GrupoAcesso getGrupoAcesso() {
        return grupoAcesso;
    }

    public void setGrupoAcesso(GrupoAcesso grupoAcesso) {
        this.grupoAcesso = grupoAcesso;
    }
}
