package com.nelcfood.model.exception.naoEncontrada;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException{
  public FormaPagamentoNaoEncontradaException() {
    super("A forma de pagamento não foi encontrada na base de dados.");
  }
}
