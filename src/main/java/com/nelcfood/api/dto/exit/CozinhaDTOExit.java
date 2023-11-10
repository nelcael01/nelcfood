package com.nelcfood.api.dto.exit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CozinhaDTOExit {
  private Long id;
  private String nome;
}
