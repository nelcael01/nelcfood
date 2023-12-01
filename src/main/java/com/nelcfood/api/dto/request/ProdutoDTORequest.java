package com.nelcfood.api.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class ProdutoDTORequest {
  @NotBlank
private String nome;
  @NotBlank
  private String descricao;
  @PositiveOrZero
  private BigDecimal preco;
  @NotNull
  private boolean ativo;
}
