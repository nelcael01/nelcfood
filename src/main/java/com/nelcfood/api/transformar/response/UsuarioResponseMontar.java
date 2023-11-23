package com.nelcfood.api.transformar.response;

import com.nelcfood.api.dto.response.UsuarioDTOResponse;
import com.nelcfood.model.entities.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioResponseMontar {

  @Autowired
  ModelMapper modelMapper;

  public UsuarioDTOResponse transformarEntidadeEmResponse(Usuario usuario) {
    return modelMapper.map(usuario, UsuarioDTOResponse.class);
  }

  public List<UsuarioDTOResponse> transformarColecaoEmResponse(List<Usuario> usuarios) {
    return usuarios.stream().map((usuario -> transformarEntidadeEmResponse(usuario))).collect(Collectors.toList());
  }
}
