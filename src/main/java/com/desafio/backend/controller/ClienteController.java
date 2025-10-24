package com.desafio.backend.controller;

import com.desafio.backend.model.Cliente;
import com.desafio.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
        try {
            if (cliente.getTelefones() != null) {
                cliente.getTelefones().forEach(t -> {
                    t.setCliente(cliente);
                    System.out.println("Telefone vinculado: " + t.getNumero());
                });
            }

            if (cliente.getEmails() != null) {
                cliente.getEmails().forEach(e -> {
                    e.setCliente(cliente);
                    System.out.println("Email vinculado: " + e.getEmail());
                });
            }

            Cliente salvo = clienteRepository.save(cliente);
            System.out.println("Cliente salvo com ID: " + salvo.getId());

            return ResponseEntity.ok(salvo);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Erro ao salvar cliente: " + e.getMessage());
        }
    }
}
