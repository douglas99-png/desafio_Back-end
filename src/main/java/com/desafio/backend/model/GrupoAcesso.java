package com.desafio.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "GrupoAcesso")
public class GrupoAcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdGrupoAcesso")
    private Long id;

    @Column(name = "NomeGrupo", nullable = false)
    private String nomeGrupo;

    @OneToMany(mappedBy = "grupoAcesso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GrupoAcessoPermissao> permissoes;

    @OneToMany(mappedBy = "grupoAcesso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GrupoAcessoUsuario> usuarios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public GrupoAcesso getGrupoAcesso() {
        return this;
    }


    public List<GrupoAcessoPermissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<GrupoAcessoPermissao> permissoes) {
        this.permissoes = permissoes;
    }

    public List<GrupoAcessoUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<GrupoAcessoUsuario> usuarios) {
        this.usuarios = usuarios;
    }
}
