package com.desafio.backend.service;

import com.desafio.backend.config.JwtUtil;
import com.desafio.backend.model.GrupoAcessoUsuario;
import com.desafio.backend.model.Permissao;
import com.desafio.backend.model.Usuario;
import com.desafio.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;


    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    public String autenticar(String login, String senha) {
        Usuario usuario = usuarioRepository.findByLogin(login).orElse(null);

        if (usuario == null) return null;
        if (!usuario.getSenha().equals(senha)) return null; // senha em texto puro

        List<String> permissoes = usuario.getGrupos().stream()
                .map(gau -> gau.getGrupoAcesso()) // 👈 lambda simples, sem type inference
                .flatMap(grupo -> grupo.getPermissoes().stream())
                .map(gaPerm -> gaPerm.getPermissao().getNomePermissao())
                .distinct()
                .collect(Collectors.toList());



        return jwtUtil.generateToken(login, permissoes); // 👈 agora funciona
    }
    public List<String> buscarPermissoesPorUsuario(String login) {
        return usuarioRepository.findPermissoesByLogin(login)
                .stream()
                .map(Permissao::getNomePermissao)
                .collect(Collectors.toList());
    }


}
