package com.nelcfood.model.entities;

import com.nelcfood.model.entities.enuns.StatusPedido;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(name = "sub_total", nullable = false)
  private BigDecimal subTotal;

  @Column(name = "taxa_frete", nullable = false)
  private BigDecimal taxaFrete;

  @Column(name = "valor_total", nullable = false)
  private BigDecimal valorTotal;

  @CreationTimestamp
  @Column(name = "data_criacao", nullable = false)
  private OffsetDateTime dataCriacao;

  @Column(name = "data_confirmacao")
  private OffsetDateTime dataConfirmacao;

  @Column(name = "data_cancelamento")
  private OffsetDateTime dataCancelamento;

  @Column(name = "data_entrega")
  private OffsetDateTime dataEntrega;

  @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
  private List<ItemPedido> itens;

  @Embedded
  private Endereco enderecoEntrega;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private StatusPedido status;

  @ManyToOne
  @JoinColumn(name = "cliente_id", nullable = false)
  private Usuario cliente;

  @ManyToOne
  @JoinColumn(name = "restaurante_id", nullable = false)
  private Restaurante restaurante;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "forma_pagamento_id", nullable = false)
  private FormaPagamento formaPagamento;

  public void calcularValorTotal() {
    getItens().forEach(ItemPedido::calcularPrecoTotal);

    this.subTotal = getItens().stream()
            .map(item -> item.getPrecoTotal())
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    this.valorTotal = this.subTotal.add(this.taxaFrete);
  }
}
