package com.nelcfood.api.dto.exit;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RestauranteDTOExit {
  private Long id;
  private String nome;
  private BigDecimal taxaFrete;
  private CozinhaDTOExit cozinha;
}
