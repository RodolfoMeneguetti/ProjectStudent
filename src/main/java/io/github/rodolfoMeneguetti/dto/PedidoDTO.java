package io.github.rodolfoMeneguetti.dto;

import io.github.rodolfoMeneguetti.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/** Json que será realizado o POST
 {
 "cliente" : 1 ,
 "total" : 100,
 "items" :
 [
 {
 "produto" : 1,
 "quantidade" : 10
 }
 ]
 }
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;

    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;

    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    private List<ItemsPedidoDTO> items;
}
