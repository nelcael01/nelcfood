package com.nelcfood.api.assembler;

import com.nelcfood.api.dto.request.EstadoDTORequest;
import com.nelcfood.model.entities.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoRequestDisassebler {

  @Autowired
  ModelMapper modelMapper;

  public Estado transfomarRequestEmEntidade(EstadoDTORequest estadoDTORequest) {
    return modelMapper.map(estadoDTORequest, Estado.class);
  }

  public void copiarRequestParaEntidade(EstadoDTORequest estadoDTORequest, Estado estado) {
    modelMapper.map(estadoDTORequest, estado);
  }
}
