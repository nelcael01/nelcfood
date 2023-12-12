package com.nelcfood.api.dto.response;

import com.nelcfood.model.entities.enuns.StatusPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class PedidoResumoDTOResponse {
  private Long id;
  private BigDecimal subTotal;
  private BigDecimal taxaFrete;
  private BigDecimal valorTotal;
  private StatusPedido status;
  private OffsetDateTime dataCriacao;
  private UsuarioDTOResponse cliente;
  private RestauranteResumoDTOResponse restaurante;
}
