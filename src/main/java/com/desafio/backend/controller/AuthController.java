package com.desafio.backend.controller;

import com.desafio.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> dados) {
        String login = dados.get("login");
        String senha = dados.get("senha");

        boolean autenticado = authService.autenticar(login, senha);

        if (autenticado) {
            return ResponseEntity.ok(Map.of("mensagem", " Login realizado com sucesso!"));
        } else {
            return ResponseEntity.status(401).body(Map.of("erro", " Usuário ou senha inválidos."));
        }
    }
}
