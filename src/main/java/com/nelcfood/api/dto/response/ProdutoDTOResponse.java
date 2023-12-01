package com.nelcfood.api.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDTOResponse {

  private Long id;
  private String nome;
  private String descricao;
  private BigDecimal preco;
  private boolean ativo;

}
