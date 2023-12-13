package com.nelcfood.api.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FormaPagamentoIdDTORequest {
  @NotNull
  private Long id;
}
