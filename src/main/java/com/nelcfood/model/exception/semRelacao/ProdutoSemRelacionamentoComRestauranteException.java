package com.nelcfood.model.exception.semRelacao;

public class ProdutoSemRelacionamentoComRestauranteException extends EntidadeSemRelacionamentoException {
  public ProdutoSemRelacionamentoComRestauranteException() {
    super("Esse produto não tem relacinamento com esse restaurante");
  }
}
