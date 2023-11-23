package com.nelcfood.api.transformar.request;

import com.nelcfood.api.dto.request.UsuarioDTORequest;
import com.nelcfood.api.dto.request.UsuarioSemSenhaDTORequest;
import com.nelcfood.model.entities.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioSemSenhaRequestDesmontar {

  @Autowired
  ModelMapper modelMapper;

  public Usuario transformarRequestEmEntidade(UsuarioSemSenhaDTORequest usuarioSemSenhaDTORequest) {
    return modelMapper.map(usuarioSemSenhaDTORequest, Usuario.class);
  }

  public void copiarRequestParaEntidade(UsuarioSemSenhaDTORequest usuarioSemSenhaDTORequest, Usuario usuario) {
    modelMapper.map(usuarioSemSenhaDTORequest, usuario);
  }
}
