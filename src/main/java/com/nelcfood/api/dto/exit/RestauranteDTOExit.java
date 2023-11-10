package com.nelcfood.api.dto.exit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteDTOExit {
  private Long id;
  private String nome;
  private BigDecimal taxaFrete;
  private CozinhaDTOExit cozinha;
}
