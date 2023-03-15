package io.github.rodolfoMeneguetti.Service;

import io.github.rodolfoMeneguetti.domain.entity.Pedido;
import io.github.rodolfoMeneguetti.domain.enums.StatusPedido;
import io.github.rodolfoMeneguetti.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional <Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
