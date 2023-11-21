package com.nelcfood.api.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GrupoDTORequest {

  @NotBlank
  private String nome;

}
