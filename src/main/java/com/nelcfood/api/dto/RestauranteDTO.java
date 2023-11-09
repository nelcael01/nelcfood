package com.nelcfood.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestauranteDTO {
  private Long id;
  private String nome;
  private BigDecimal taxaFrete;
  private CozinhaDTO cozinha;
}
