package com.nelcfood.model.exception.naoEncontrada;

import com.nelcfood.model.exception.NegocioException;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }
}
