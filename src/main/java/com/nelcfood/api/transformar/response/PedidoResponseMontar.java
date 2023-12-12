package com.nelcfood.api.transformar.response;

import com.nelcfood.api.dto.response.PedidoDTOResponse;
import com.nelcfood.model.entities.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoResponseMontar {

  @Autowired
  ModelMapper modelMapper;

  public PedidoDTOResponse transformarEntidadeEmResponse(Pedido pedido) {
    return modelMapper.map(pedido, PedidoDTOResponse.class);
  }

  public List<PedidoDTOResponse> transformarEntidadeEmColecao(List<Pedido> pedidos) {
    return pedidos.stream().map((pedido) -> transformarEntidadeEmResponse(pedido))
            .collect(Collectors.toList());
  }

}
