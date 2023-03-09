package io.github.rodolfoMeneguetti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Integer cliente;
    private BigDecimal total;
    private List<ItemsPedidoDTO> items;
}
