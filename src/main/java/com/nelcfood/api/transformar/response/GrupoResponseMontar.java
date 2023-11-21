package com.nelcfood.api.transformar.response;

import com.nelcfood.api.dto.response.GrupoDTOResponse;
import com.nelcfood.model.entities.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoResponseMontar {

  @Autowired
  ModelMapper modelMapper;

  public GrupoDTOResponse tranformarEntidadeEmResponse(Grupo grupo) {
    return modelMapper.map(grupo, GrupoDTOResponse.class);
  }

  public List<GrupoDTOResponse> tranformarColecaoEmResponse(List<Grupo> grupos) {
    return grupos.stream().map((grupo) -> tranformarEntidadeEmResponse(grupo)).collect(Collectors.toList());
  }

}
