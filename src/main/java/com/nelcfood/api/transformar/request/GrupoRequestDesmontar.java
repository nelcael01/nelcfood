package com.nelcfood.api.transformar.request;

import com.nelcfood.api.dto.request.GrupoDTORequest;
import com.nelcfood.model.entities.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoRequestDesmontar {
  @Autowired
  ModelMapper modelMapper;

  public Grupo transformarRequestEmEntidade(GrupoDTORequest grupoDTORequest) {
    return modelMapper.map(grupoDTORequest, Grupo.class);
  }

  public void copiarRequestParaEntidade(GrupoDTORequest grupoDTORequest, Grupo grupo) {
    modelMapper.map(grupoDTORequest, grupo);
  }
}
