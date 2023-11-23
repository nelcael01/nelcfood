package com.nelcfood.api.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UsuarioAtualizarSenhaDTORequest {
  @NotBlank
  private String senhaAtual;
  @NotBlank
  private String novaSenha;
}
