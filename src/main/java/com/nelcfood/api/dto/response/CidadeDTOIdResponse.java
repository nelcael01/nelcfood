package com.nelcfood.api.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CidadeDTOIdResponse {
  @NotNull
  private Long id;
}
