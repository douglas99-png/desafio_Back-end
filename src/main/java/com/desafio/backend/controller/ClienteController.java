package com.desafio.backend.controller;

import com.desafio.backend.model.Cliente;
import com.desafio.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @PostMapping("/inserir")
    public ResponseEntity<?> salvarOuAtualizarCliente(@RequestBody Cliente cliente) {
        try {
            Optional<Cliente> existentePorCpf = clienteRepository.findByCpf(cliente.getCpf());

            if (existentePorCpf.isPresent()) {
                Cliente existente = existentePorCpf.get();

                if (cliente.getId() == null || !existente.getId().equals(cliente.getId())) {
                    return ResponseEntity.badRequest()
                            .body("Já existe um cliente cadastrado com este CPF: " + cliente.getCpf());
                }
            }

            boolean isUpdate = cliente.getId() != null && clienteRepository.existsById(cliente.getId());

            if (isUpdate) {
                Cliente existente = clienteRepository.findById(cliente.getId())
                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado para atualização: " + cliente.getId()));

                existente.setNome(cliente.getNome());
                existente.setCpf(cliente.getCpf());
                existente.setCep(cliente.getCep());
                existente.setCidade(cliente.getCidade());
                existente.setUf(cliente.getUf());
                existente.setBairro(cliente.getBairro());
                existente.setLogradouro(cliente.getLogradouro());
                existente.setComplemento(cliente.getComplemento());
                existente.setDataAtualizacao(LocalDateTime.now());

                existente.getEmails().clear();
                if (cliente.getEmails() != null) {
                    cliente.getEmails().forEach(e -> {
                        e.setCliente(existente);
                        existente.getEmails().add(e);
                        System.out.println("Email atualizado: " + e.getEmail());
                    });
                }

                existente.getTelefones().clear();
                if (cliente.getTelefones() != null) {
                    cliente.getTelefones().forEach(t -> {
                        t.setCliente(existente);
                        existente.getTelefones().add(t);
                        System.out.println("Telefone atualizado: " + t.getNumero());
                    });
                }

                Cliente atualizado = clienteRepository.save(existente);
                System.out.println("Cliente atualizado com ID: " + atualizado.getId());
                return ResponseEntity.ok(atualizado);
            }

            if (cliente.getTelefones() != null) {
                cliente.getTelefones().forEach(t -> t.setCliente(cliente));
            }
            if (cliente.getEmails() != null) {
                cliente.getEmails().forEach(e -> e.setCliente(cliente));
            }
            cliente.setDataCadastro(LocalDateTime.now());
            cliente.setDataAtualizacao(LocalDateTime.now());

            Cliente salvo = clienteRepository.save(cliente);
            System.out.println("Cliente salvo com ID: " + salvo.getId());

            return ResponseEntity.ok(salvo);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Erro ao salvar/atualizar cliente: " + e.getMessage());
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id) {
        try {
            if (!clienteRepository.existsById(id)) {
                return ResponseEntity.status(404).body("Cliente não encontrado com o ID: " + id);
            }

            clienteRepository.deleteById(id);
            System.out.println("Cliente deletado com ID: " + id);

            return ResponseEntity.ok("Cliente removido com sucesso.");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Erro ao excluir cliente: " + e.getMessage());
        }
    }



    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listarClientes() {
        try {
            List<Cliente> clientes = clienteRepository.findAll();

            if (clientes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(null);
        }
    }

}
