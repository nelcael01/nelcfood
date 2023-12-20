package com.nelcfood.api.dto.request;

import com.nelcfood.model.entities.ItemPedido;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class PedidoDTORequest {
  @Valid
  @NotNull
  private RestauranteIdDTORequest restaurante;
  @Valid
  @NotNull
  private FormaPagamentoIdDTORequest formaPagamento;
  @Valid
  @NotNull
  private EnderecoDTORequest enderecoEntrega;
  @Valid
  @Size(min = 1)
  @NotNull
  private List<ItemPedidoDTORequest> itens;
}
