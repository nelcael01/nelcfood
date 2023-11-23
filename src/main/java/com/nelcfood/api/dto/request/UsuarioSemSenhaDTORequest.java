package com.nelcfood.api.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UsuarioSemSenhaDTORequest {
  @NotBlank
  private String nome;
  @NotBlank
  @Email
  private String email;
}
