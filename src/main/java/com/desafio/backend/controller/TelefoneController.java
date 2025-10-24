package com.desafio.backend.controller;

import com.desafio.backend.model.Telefone;
import com.desafio.backend.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {

    @Autowired
    private TelefoneRepository telefoneRepository;

    @GetMapping
    public List<Telefone> listar() {
        return telefoneRepository.findAll();
    }

    @PostMapping
    public Telefone criar(@RequestBody Telefone telefone) {
        return telefoneRepository.save(telefone);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        telefoneRepository.deleteById(id);
    }
}
