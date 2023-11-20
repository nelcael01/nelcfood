package com.nelcfood.api.transformar.request;

import com.nelcfood.api.dto.request.CidadeDTORequest;
import com.nelcfood.model.entities.Cidade;
import com.nelcfood.model.entities.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeRequestDesmontar {

  @Autowired
  ModelMapper modelMapper;

  public Cidade transformarRequestEmEntidade(CidadeDTORequest cidadeDTORequest) {
    return modelMapper.map(cidadeDTORequest, Cidade.class);
  }

  public void copiarRequestParaEntidade(CidadeDTORequest cidadeDTORequest, Cidade cidade) {
    cidade.setEstado(new Estado());
    modelMapper.map(cidadeDTORequest, cidade);
  }
}
