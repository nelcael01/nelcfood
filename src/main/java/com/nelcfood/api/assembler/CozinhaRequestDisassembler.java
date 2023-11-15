package com.nelcfood.api.assembler;

import com.nelcfood.api.dto.request.CozinhaDTORequest;
import com.nelcfood.model.entities.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaRequestDisassembler {

  @Autowired
  ModelMapper modelMapper;

  public Cozinha transformarRequestEmEntidade(CozinhaDTORequest cozinhaDTORequest) {
    return modelMapper.map(cozinhaDTORequest, Cozinha.class);
  }

  public void copiarRequestParaEntidade(CozinhaDTORequest cozinhaDTORequest, Cozinha cozinha) {
    modelMapper.map(cozinhaDTORequest, cozinha);
  }
}
