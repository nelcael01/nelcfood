package com.nelcfood.exception.naoEncontrada;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public EstadoNaoEncontradoException() {
        super("O Estado não foi encontrado na base de dados.");
    }

}
