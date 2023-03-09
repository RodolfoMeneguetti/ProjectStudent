package io.github.rodolfoMeneguetti.Service;

import io.github.rodolfoMeneguetti.domain.entity.Pedido;
import io.github.rodolfoMeneguetti.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

}
