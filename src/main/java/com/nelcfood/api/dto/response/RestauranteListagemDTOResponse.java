package com.nelcfood.api.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class RestauranteListagemDTOResponse {
  private Long id;
  private String nome;
  private BigDecimal taxaFrete;
  private CozinhaDTOResponse cozinha;
}
