package com.nelcfood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public EstadoNaoEncontradoException() {
        super("O Estado não foi encontrado na base de dados.");
    }

}
