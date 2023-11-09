package com.nelcfood.api.dto.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CozinhaIdDTOInput {

  @NotNull
  private Long id;
}
