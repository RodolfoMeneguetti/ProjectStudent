package io.github.rodolfoMeneguetti.Service.impl;

import io.github.rodolfoMeneguetti.Service.PedidoService;
import io.github.rodolfoMeneguetti.domain.entity.Cliente;
import io.github.rodolfoMeneguetti.domain.entity.ItemPedido;
import io.github.rodolfoMeneguetti.domain.entity.Pedido;
import io.github.rodolfoMeneguetti.domain.entity.Produto;
import io.github.rodolfoMeneguetti.domain.repository.RepositoryClient;
import io.github.rodolfoMeneguetti.domain.repository.RepositoryItemPedido;
import io.github.rodolfoMeneguetti.domain.repository.RepositoryPedido;
import io.github.rodolfoMeneguetti.domain.repository.RepositoryProdutos;
import io.github.rodolfoMeneguetti.dto.ItemsPedidoDTO;
import io.github.rodolfoMeneguetti.dto.PedidoDTO;
import io.github.rodolfoMeneguetti.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final RepositoryPedido pedidoRepository;
    private final RepositoryClient clienteRepository;
    private final RepositoryProdutos produtosRepository;
    private final RepositoryItemPedido itemPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar ( PedidoDTO dto ){
        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow( () -> new RegraNegocioException("Código de cliente invãlido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = convertItens(pedido, dto.getItems());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto (Integer id){
        return pedidoRepository.findByIdFetchItens(id);
    }

    private List<ItemPedido> convertItens (Pedido pedido, List<ItemsPedidoDTO> items ) {
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar um pedido sem items");
        }
        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto =  produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException("Código de produto invalido = "
                                    + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

}
