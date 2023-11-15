package com.nelcfood.api.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CozinhaDTORequest {

  @NotBlank
  private String nome;
}
