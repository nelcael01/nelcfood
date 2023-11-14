package com.nelcfood.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteDTOResponse {
  private Long id;
  private String nome;
  private BigDecimal taxaFrete;
  private CozinhaDTOResponse cozinha;
}
