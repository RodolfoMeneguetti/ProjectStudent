package io.github.rodolfoMeneguetti.domain.entity;

import javax.xml.crypto.Data;
import java.math.BigDecimal;

public class Pedido {

    private Integer id;
    private Cliente cliente;
    private Data dataPedido;
    private BigDecimal total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Data getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Data dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
