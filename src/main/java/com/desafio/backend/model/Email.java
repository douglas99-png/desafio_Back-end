package com.desafio.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEmail")
    private Long id;

    @Column(name = "Email", nullable = false, length = 120)
    private String email;

    @ManyToOne
    @JoinColumn(name = "IdCliente", nullable = false)
    @JsonBackReference
    private Cliente cliente;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
