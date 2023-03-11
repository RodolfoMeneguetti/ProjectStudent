package io.github.rodolfoMeneguetti.controller;

import io.github.rodolfoMeneguetti.Service.PedidoService;
import io.github.rodolfoMeneguetti.domain.entity.ItemPedido;
import io.github.rodolfoMeneguetti.domain.entity.Pedido;
import io.github.rodolfoMeneguetti.dto.InformacoesItemPedidoDTO;
import io.github.rodolfoMeneguetti.dto.InformacoesPedidoDTO;
import io.github.rodolfoMeneguetti.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById (@PathVariable Integer id) {
        return service
                .obterPedidoCompleto(id)
                .map(p -> converterPedido(p)
                )
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido Nao encontrado."));
    }

    private InformacoesPedidoDTO converterPedido (Pedido pedido){
       return  InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .items(converterItemPedido(pedido.getItens()))
               .build();
    }

    private List<InformacoesItemPedidoDTO> converterItemPedido (List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(
                item -> InformacoesItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .quantidade(item.getQuantidade())
                        .build()).collect(Collectors.toList());
    }
}
