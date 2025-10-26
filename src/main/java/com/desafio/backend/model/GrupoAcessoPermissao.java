package com.desafio.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GrupoAcesso_Permissao")
public class GrupoAcessoPermissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdGrupoAcesso_Permissao")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IdGrupoAcesso", nullable = false)
    private GrupoAcesso grupoAcesso;

    @ManyToOne
    @JoinColumn(name = "IdPermissao", nullable = false)
    private Permissao permissao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GrupoAcesso getGrupoAcesso() {
        return grupoAcesso;
    }

    public void setGrupoAcesso(GrupoAcesso grupoAcesso) {
        this.grupoAcesso = grupoAcesso;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }
}
