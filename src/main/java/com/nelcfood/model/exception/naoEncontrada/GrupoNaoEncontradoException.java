package com.nelcfood.model.exception.naoEncontrada;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public GrupoNaoEncontradoException() {
        super("O Grupo não foi encontrado na base de dados.");
    }
}
