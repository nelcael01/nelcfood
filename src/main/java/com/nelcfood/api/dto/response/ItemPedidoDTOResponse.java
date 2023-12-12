package com.nelcfood.api.dto.response;

import com.nelcfood.model.entities.Produto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedidoDTOResponse {

  private Long id;
  private int quantidade;
  private BigDecimal precoUnitario;
  private BigDecimal precoTotal;
  private String observacao;
  private Long produtoId;
  private String produtoNome;
}
