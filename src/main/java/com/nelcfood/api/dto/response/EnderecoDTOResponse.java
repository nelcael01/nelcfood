package com.nelcfood.api.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnderecoDTOResponse {

  private String cep;
  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private CidadeDTOResumoResponse cidade;

}
