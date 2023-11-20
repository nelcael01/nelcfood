package com.nelcfood.api.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CidadeDTOResumoResponse {
  private Long id;
  private String nome;
  private String estado;
}
