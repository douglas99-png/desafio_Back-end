package com.desafio.backend.repository;

import com.desafio.backend.model.Permissao;
import com.desafio.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByLogin(String login);

    @Query("""
    SELECT p
    FROM Usuario u
    JOIN u.grupos gu
    JOIN gu.grupoAcesso ga
    JOIN ga.permissoes gp
    JOIN gp.permissao p
    WHERE u.login = :login
""")
    List<Permissao> findPermissoesByLogin(@Param("login") String login);


}
