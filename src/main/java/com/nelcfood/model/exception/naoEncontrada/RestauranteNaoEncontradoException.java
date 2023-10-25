package com.nelcfood.model.exception.naoEncontrada;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
    public RestauranteNaoEncontradoException() {
        super("O Restaurante não foi encontrado na base de dados.");
    }


}
