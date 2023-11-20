package com.nelcfood.api.dto.request;

import com.nelcfood.api.dto.response.CidadeDTOIdResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EnderecoDTORequest {
  @NotBlank
  private String cep;
  @NotBlank
  private String logradouro;
  @NotBlank
  private String numero;
  private String complemento;
  @NotBlank
  private String bairro;
  @Valid
  @NotNull
  private CidadeDTOIdResponse cidade;

}
