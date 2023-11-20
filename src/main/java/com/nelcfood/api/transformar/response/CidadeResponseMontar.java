package com.nelcfood.api.transformar.response;

import com.nelcfood.api.dto.response.CidadeDTOResponse;
import com.nelcfood.model.entities.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeResponseMontar {

  @Autowired
  ModelMapper modelMapper;

  public CidadeDTOResponse transformarEntidadeEmResponse(Cidade cidade) {
    return modelMapper.map(cidade, CidadeDTOResponse.class);
  }

  public List<CidadeDTOResponse> transformarColecaoEmResponse(List<Cidade> cidades) {
    return cidades.stream().map((cidade) -> transformarEntidadeEmResponse(cidade))
            .collect(Collectors.toList());
  }
}
