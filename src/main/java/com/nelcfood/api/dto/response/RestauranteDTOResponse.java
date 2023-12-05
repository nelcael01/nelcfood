package com.nelcfood.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class RestauranteDTOResponse {
  private Long id;
  private String nome;
  private BigDecimal taxaFrete;
  private CozinhaDTOResponse cozinha;
  private Boolean ativo;
  private Boolean aberto;
  private EnderecoDTOResponse endereco;
}
