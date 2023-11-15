package com.nelcfood.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CozinhaDTOResponse {
  private Long id;
  private String nome;
}
