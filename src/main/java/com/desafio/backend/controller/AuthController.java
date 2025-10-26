package com.desafio.backend.controller;

import com.desafio.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> dados) {
        String login = dados.get("login");
        String senha = dados.get("senha");

        String token = authService.autenticar(login, senha);

        List<String> permissoes = authService.buscarPermissoesPorUsuario(login);

        if (token == null)
            return ResponseEntity.status(401).body(Map.of("erro", "Usuário ou senha inválidos."));

        return ResponseEntity.ok(Map.of(
                "token", token,
                "user", login,
                "authorities", permissoes
        ));
    }
}
