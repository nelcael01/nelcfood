package com.nelcfood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
    public RestauranteNaoEncontradoException() {
        super("O Restaurante não foi encontrado na base de dados.");
    }


}
