package com.desafio.backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Permissao")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPermissao")
    private Long id;

    @Column(name = "NomePermissao", nullable = false, unique = true)
    private String nomePermissao;

    @OneToMany(mappedBy = "permissao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GrupoAcessoPermissao> gruposAcesso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePermissao() {
        return nomePermissao;
    }

    public void setNomePermissao(String nomePermissao) {
        this.nomePermissao = nomePermissao;
    }

    public List<GrupoAcessoPermissao> getGruposAcesso() {
        return gruposAcesso;
    }

    public void setGruposAcesso(List<GrupoAcessoPermissao> gruposAcesso) {
        this.gruposAcesso = gruposAcesso;
    }
}
