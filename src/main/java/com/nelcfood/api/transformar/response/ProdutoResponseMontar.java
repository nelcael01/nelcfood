package com.nelcfood.api.transformar.response;

import com.nelcfood.api.dto.response.ProdutoDTOResponse;
import com.nelcfood.model.entities.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoResponseMontar {

  @Autowired
  ModelMapper modelMapper;

  public ProdutoDTOResponse transformarEntidadeEmResponse(Produto produto) {
    return modelMapper.map(produto, ProdutoDTOResponse.class);
  }

  public List<ProdutoDTOResponse> transformarColecaoEmResponse(List<Produto> produtos) {
    return produtos.stream().map((produto) -> transformarEntidadeEmResponse(produto)).collect(Collectors.toList());
  }
}
