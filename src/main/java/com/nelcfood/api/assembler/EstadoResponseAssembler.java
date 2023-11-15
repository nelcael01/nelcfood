package com.nelcfood.api.assembler;

import com.nelcfood.api.dto.response.EstadoDTOResponse;
import com.nelcfood.model.entities.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoResponseAssembler {

  @Autowired
  ModelMapper modelMapper;


  public EstadoDTOResponse transformarEntidadeEmResponse(Estado estado) {
    return modelMapper.map(estado, EstadoDTOResponse.class);
  }

  public List<EstadoDTOResponse> tranformarColecaoEmResponse(List<Estado> estados) {
    return estados.stream().map((estado) -> transformarEntidadeEmResponse(estado))
            .collect(Collectors.toList());
  }
}
