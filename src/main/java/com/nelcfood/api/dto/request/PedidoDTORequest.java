package com.nelcfood.api.dto.request;

import com.nelcfood.model.entities.ItemPedido;
import lombok.Data;

import java.util.List;

@Data
public class PedidoDTORequest {
  private RestauranteIdDTORequest restaurante;
  private FormaPagamentoIdDTORequest formaPagamento;
  private EnderecoDTORequest enderecoEntrega;
  private List<ItemPedidoDTORequest> itens;
}
