package com.nelcfood.model.exception.naoEncontrada;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public PedidoNaoEncontradoException() {
        super("O Pedido não foi encontrado na base de dados.");
    }

}
