package com.nelcfood.api.dto.response;

import com.nelcfood.model.entities.ItemPedido;
import com.nelcfood.model.entities.enuns.StatusPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class PedidoDTOResponse {
  private String codigo;
  private BigDecimal subTotal;
  private BigDecimal taxaFrete;
  private BigDecimal valorTotal;
  private StatusPedido status;
  private OffsetDateTime dataCriacao;
  private OffsetDateTime dataConfirmacao;
  private OffsetDateTime dataCancelamento;
  private OffsetDateTime dataEntrega;
  private List<ItemPedidoDTOResponse> itens;
  private EnderecoDTOResponse enderecoEntrega;
  private UsuarioDTOResponse cliente;
  private RestauranteResumoDTOResponse restaurante;
  private FormaPagamentoDTOResponse formaPagamento;
}
