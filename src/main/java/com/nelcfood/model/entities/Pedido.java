package com.nelcfood.model.entities;

import com.nelcfood.model.entities.enuns.StatusPedido;
import com.nelcfood.model.exception.NegocioException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(nullable = false)
  private String codigo;

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
  private StatusPedido status = StatusPedido.CRIADO;

  @ManyToOne
  @JoinColumn(name = "cliente_id", nullable = false)
  private Usuario cliente;

  @ManyToOne
  @JoinColumn(name = "restaurante_id", nullable = false)
  private Restaurante restaurante;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "forma_pagamento_id", nullable = false)
  private FormaPagamento formaPagamento;

  public void confirmacao() {
    setStatus(StatusPedido.CONFIRMADO);
    setDataConfirmacao(OffsetDateTime.now());
  }

  public void entrega() {
    setStatus(StatusPedido.ENTREGUE);
    setDataEntrega(OffsetDateTime.now());
  }

  public void cancelamento() {
    setStatus(StatusPedido.CANCELADO);
    setDataCancelamento(OffsetDateTime.now());
  }

  private void setStatus(StatusPedido novoStatus) {
    if (getStatus().naoPodeAlterarPara(novoStatus)) {
      throw new NegocioException(String.format("O status do pedido %s não pode ser alterado de %s para %s", getCodigo(), getStatus(), novoStatus));
    }
    this.status = novoStatus;
  }

  public void calcularValorTotal() {
    getItens().forEach(ItemPedido::calcularPrecoTotal);

    this.subTotal = getItens().stream()
            .map(item -> item.getPrecoTotal())
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    this.valorTotal = this.subTotal.add(this.taxaFrete);
  }

  @PrePersist
  private void gerarCodigo() {
    setCodigo(UUID.randomUUID().toString());
  }
}
