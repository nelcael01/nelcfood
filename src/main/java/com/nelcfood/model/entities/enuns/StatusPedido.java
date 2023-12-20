package com.nelcfood.model.entities.enuns;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
  CRIADO,
  CONFIRMADO(CRIADO),
  ENTREGUE(CONFIRMADO),
  CANCELADO(CRIADO);

  private List<StatusPedido> statusAnteriores;

  StatusPedido(StatusPedido... statusAnteriores) {
    this.statusAnteriores = Arrays.asList(statusAnteriores);
  }

  public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
    return !novoStatus.statusAnteriores.contains(this);
  }

}
