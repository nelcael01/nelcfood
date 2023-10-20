package com.nelcfood.exception;

import lombok.Getter;

@Getter
public class EntidadeNaoEncontrada extends RuntimeException {

    public EntidadeNaoEncontrada(String msg) {
        super(msg);
    }

}
