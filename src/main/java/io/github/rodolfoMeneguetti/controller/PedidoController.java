package io.github.rodolfoMeneguetti.controller;

import io.github.rodolfoMeneguetti.Service.PedidoService;
import io.github.rodolfoMeneguetti.domain.entity.Pedido;
import io.github.rodolfoMeneguetti.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save ( @RequestBody PedidoDTO dto ){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }


}
