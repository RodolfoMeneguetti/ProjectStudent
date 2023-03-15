package io.github.rodolfoMeneguetti.exception;

public class PedidoNaoEncontradoException extends RuntimeException{

    public PedidoNaoEncontradoException (){
        super("Pedido Não Encontrado!");
    }
}
