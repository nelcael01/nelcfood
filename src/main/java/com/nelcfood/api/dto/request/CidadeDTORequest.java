package com.nelcfood.api.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CidadeDTORequest {

  @NotBlank
  private String nome;

  @NotNull
  @Valid
  private EstadoIdDTORequest estado;
}
