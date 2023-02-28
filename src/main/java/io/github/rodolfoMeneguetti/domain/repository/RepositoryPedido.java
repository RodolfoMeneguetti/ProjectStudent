package io.github.rodolfoMeneguetti.domain.repository;

import io.github.rodolfoMeneguetti.domain.entity.Cliente;
import io.github.rodolfoMeneguetti.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryPedido extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente (Cliente cliente);
}
