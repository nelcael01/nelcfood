package com.nelcfood.exception.naoEncontrada;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
    public CidadeNaoEncontradaException() {
        super("A Cidade não foi encontrado na base de dados.");
    }

}
