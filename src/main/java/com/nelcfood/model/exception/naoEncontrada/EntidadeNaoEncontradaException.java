package com.nelcfood.model.exception.naoEncontrada;

import com.nelcfood.model.exception.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }
}
