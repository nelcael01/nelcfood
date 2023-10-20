package com.nelcfood.exception;

import lombok.Getter;

@Getter
public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }

}
