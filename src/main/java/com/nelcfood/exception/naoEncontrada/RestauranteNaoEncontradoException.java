package com.nelcfood.exception.naoEncontrada;

import com.nelcfood.exception.naoEncontrada.EntidadeNaoEncontradaException;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
    public RestauranteNaoEncontradoException() {
        super("O Restaurante não foi encontrado na base de dados.");
    }


}
