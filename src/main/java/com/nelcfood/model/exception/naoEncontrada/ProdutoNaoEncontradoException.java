package com.nelcfood.model.exception.naoEncontrada;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ProdutoNaoEncontradoException() {
        super("O Produto não foi encontrado na base de dados.");
    }

}
