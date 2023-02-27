package io.github.rodolfoMeneguetti.domain.repository;

import io.github.rodolfoMeneguetti.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RepositoryClient extends JpaRepository<Cliente, Integer> {



    List<Cliente> findByNomeLike(String nome);
}


