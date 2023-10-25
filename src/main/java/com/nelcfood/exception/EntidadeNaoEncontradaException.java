package com.nelcfood.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends NegocioException {
    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }


}
