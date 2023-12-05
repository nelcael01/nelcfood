package com.nelcfood.model.exception.naoEncontrada;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException{
  public PermissaoNaoEncontradaException() {
    super("A permissão não foi encontrada na base de dados.");
  }
}
