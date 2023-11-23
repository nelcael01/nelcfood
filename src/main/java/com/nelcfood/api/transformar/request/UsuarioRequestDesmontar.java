package com.nelcfood.api.transformar.request;

import com.nelcfood.api.dto.request.UsuarioDTORequest;
import com.nelcfood.model.entities.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRequestDesmontar {

  @Autowired
  ModelMapper modelMapper;

  public Usuario transformarRequestEmEntidade(UsuarioDTORequest usuarioDTORequest) {
    return modelMapper.map(usuarioDTORequest, Usuario.class);
  }

}
