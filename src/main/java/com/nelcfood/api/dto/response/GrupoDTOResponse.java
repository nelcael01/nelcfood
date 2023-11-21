package com.nelcfood.api.dto.response;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GrupoDTOResponse {
  private Long id;
  private String nome;
}
