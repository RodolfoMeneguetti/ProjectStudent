package io.github.rodolfoMeneguetti.domain.repository;

import io.github.rodolfoMeneguetti.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryProdutos extends JpaRepository<Produto, Integer> {

}
