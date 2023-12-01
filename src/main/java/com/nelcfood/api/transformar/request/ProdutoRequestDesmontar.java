package com.nelcfood.api.transformar.request;

import com.nelcfood.api.dto.request.ProdutoDTORequest;
import com.nelcfood.model.entities.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoRequestDesmontar {

  @Autowired
  ModelMapper modelMapper;

  public Produto transformarRequestEmEntidade(ProdutoDTORequest produtoDTORequest) {
    return modelMapper.map(produtoDTORequest, Produto.class);
  }

  public void copiarRequestParaEntidade(ProdutoDTORequest produtoDTORequest, Produto produto) {
    modelMapper.map(produtoDTORequest, produto);
  }
}
