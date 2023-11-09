package com.nelcfood.api.dto.input;

import com.nelcfood.core.validation.GroupsValidation;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;

public class RestauranteDTOInput {
  @NotBlank
  private String nome;
  @NotNull
  @PositiveOrZero
  private BigDecimal taxaFrete;
  @Valid
  @NotNull
  private CozinhaIdDTOInput cozinha;

}
