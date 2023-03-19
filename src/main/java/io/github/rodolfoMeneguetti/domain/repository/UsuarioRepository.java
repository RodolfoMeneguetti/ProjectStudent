package io.github.rodolfoMeneguetti.domain.repository;

import io.github.rodolfoMeneguetti.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUserName (String userName);

}
