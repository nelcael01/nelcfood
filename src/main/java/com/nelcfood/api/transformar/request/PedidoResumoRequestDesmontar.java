package com.nelcfood.api.transformar.request;

import com.nelcfood.api.dto.request.PedidoDTORequest;
import com.nelcfood.model.entities.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoResumoRequestDesmontar {

  @Autowired
  ModelMapper modelMapper;

  public Pedido transformarRequestEmEntity(PedidoDTORequest pedidoDTORequest) {
    return modelMapper.map(pedidoDTORequest, Pedido.class);
  }
}
