package com.nelcfood.api.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EstadoIdDTORequest {
  @NotNull
  private Long id;
}
