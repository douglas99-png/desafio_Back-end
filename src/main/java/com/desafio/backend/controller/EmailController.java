package com.desafio.backend.controller;

import com.desafio.backend.model.Email;
import com.desafio.backend.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailRepository emailRepository;

    @GetMapping
    public List<Email> listar() {
        return emailRepository.findAll();
    }

    @PostMapping
    public Email criar(@RequestBody Email email) {
        return emailRepository.save(email);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        emailRepository.deleteById(id);
    }
}
