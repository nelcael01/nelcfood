package com.nelcfood.model.exception;

public class NegocioException extends RuntimeException {
    public NegocioException(String msg) {
        super(msg);
    }
    public NegocioException(String msg, Throwable causa) {
        super(msg, causa);
    }
}
