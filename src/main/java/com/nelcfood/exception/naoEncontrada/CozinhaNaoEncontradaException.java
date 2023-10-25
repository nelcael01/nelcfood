package com.nelcfood.exception.naoEncontrada;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
    public CozinhaNaoEncontradaException() {
        super("A Cozinha não foi encontrado na base de dados.");
    }

}
