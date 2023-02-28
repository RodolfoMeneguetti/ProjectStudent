package io.github.rodolfoMeneguetti.domain.repository;

import io.github.rodolfoMeneguetti.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RepositoryClient extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeLike(String nome);

    @Query("DELETE FROM Cliente c WHERE c.nome LIKE %:nome%")
    @Modifying
    void deleteByNomeLike (@Param("nome") String nome);

    boolean existsByNome(String nome);

    @Query( "Select c from Cliente c left join fetch c.pedidos where c.id =:id " )
    Cliente findClienteFetchPedidos(Integer id);

}


