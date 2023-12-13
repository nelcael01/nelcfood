package com.nelcfood.api.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class ItemPedidoDTORequest {
  @NotNull
  @PositiveOrZero
  private int quantidade;
  @NotNull
  private Long produtoId;
  private String observacao;
}
