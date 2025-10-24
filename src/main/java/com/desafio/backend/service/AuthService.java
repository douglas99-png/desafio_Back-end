package com.desafio.backend.service;

import com.desafio.backend.model.Usuario;
import com.desafio.backend.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean autenticar(String login, String senha) {
        return usuarioRepository.findByLogin(login)
                .map(user -> user.getSenha().equals(senha)) // simples: sem criptografia
                .orElse(false);
    }
}
