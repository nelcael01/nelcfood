package com.nelcfood.api.transformar.response;

import com.nelcfood.api.dto.response.PermissaoDTOResponse;
import com.nelcfood.model.entities.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissaoResponseMontar {

  @Autowired
  ModelMapper modelMapper;

  public PermissaoDTOResponse transformarEntidadeEmResponse(Permissao permissao) {
    return modelMapper.map(permissao, PermissaoDTOResponse.class);
  }

  public List<PermissaoDTOResponse> transformarColecaoEmResponse(List<Permissao> permissaos) {
    return permissaos.stream().map((permissao) -> transformarEntidadeEmResponse(permissao)).collect(Collectors.toList());
  }
}
