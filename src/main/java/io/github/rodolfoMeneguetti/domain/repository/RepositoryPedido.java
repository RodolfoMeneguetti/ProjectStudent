package io.github.rodolfoMeneguetti.domain.repository;

import io.github.rodolfoMeneguetti.domain.entity.Cliente;
import io.github.rodolfoMeneguetti.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepositoryPedido extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente (Cliente cliente);

    @Query("SELECT p FROM Pedido p left join fetch p.itens where p.id =:id ")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
