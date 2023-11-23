package com.nelcfood.model.exception.naoEncontrada;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

  public UsuarioNaoEncontradoException() {
    super("O Usuário não foi encontrado na base de dados.");
  }
}
