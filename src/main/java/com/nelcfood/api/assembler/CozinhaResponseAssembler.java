package com.nelcfood.api.assembler;

import com.nelcfood.api.dto.response.CozinhaDTOResponse;
import com.nelcfood.model.entities.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaResponseAssembler {
  @Autowired
  ModelMapper modelMapper;

  public CozinhaDTOResponse tranformarEntidadeEmResponse(Cozinha cozinha) {
    return modelMapper.map(cozinha, CozinhaDTOResponse.class);
  }

  public List<CozinhaDTOResponse> transformarColecaoEmResponse(List<Cozinha> cozinhas) {
    return cozinhas.stream().map
            ((cozinha) -> tranformarEntidadeEmResponse(cozinha)).collect(Collectors.toList());
  }
}
