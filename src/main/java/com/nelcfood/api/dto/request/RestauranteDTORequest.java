package com.nelcfood.api.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class RestauranteDTORequest {
  @NotBlank
  private String nome;
  @NotNull
  @PositiveOrZero
  private BigDecimal taxaFrete;
  @Valid
  @NotNull
  private CozinhaIdDTORequest cozinha;
  @Valid
  @NotNull
  private EnderecoDTORequest endereco;
  private Boolean ativo;

}
