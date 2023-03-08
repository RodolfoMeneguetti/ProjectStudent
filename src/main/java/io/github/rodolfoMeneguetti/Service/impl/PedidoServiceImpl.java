package io.github.rodolfoMeneguetti.Service.impl;

import io.github.rodolfoMeneguetti.Service.PedidoService;
import io.github.rodolfoMeneguetti.domain.repository.RepositoryPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private RepositoryPedido repository;

}
