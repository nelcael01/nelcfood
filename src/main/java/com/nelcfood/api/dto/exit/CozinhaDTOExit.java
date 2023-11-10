package com.nelcfood.api.dto.exit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CozinhaDTOExit {
  private Long id;
  private String nome;
}
