package com.nelcfood.model.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(name = "taxa_frete", nullable = false)
  private BigDecimal taxaFrete;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cozinha_id", nullable = false)
  private Cozinha cozinha;

  @OneToMany(mappedBy = "restaurante")
  private List<Produto> produtos = new ArrayList<>();

  @ManyToMany
  @JoinTable(name = "restaurante_forma_pagamento",
          joinColumns = @JoinColumn(name = "restaurante_id"),
          inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
  private Set<FormaPagamento> formasPagamento = new HashSet<>();

  @Embedded
  private Endereco endereco;

  @Column(nullable = false)
  private boolean ativo = Boolean.TRUE;

  @Column(nullable = false)
  private Boolean aberto;

  @CreationTimestamp
  @Column(name = "data_cadastro", nullable = false)
  private OffsetDateTime dataCadastro;

  @UpdateTimestamp
  @Column(name = "data_atualizacao", nullable = false)
  private OffsetDateTime dataAtualizacao;

  @ManyToMany
  @JoinTable(name = "restaurante_usuario_responsavel",
          joinColumns = @JoinColumn(name = "restaurante_id"),
          inverseJoinColumns = @JoinColumn(name = "usuario_id"))
  private Set<Usuario> usuarios = new HashSet<>();

  public void ativar() {
    setAtivo(true);
  }

  public void inativar() {
    setAtivo(false);
  }

  public void fechar() {
    setAberto(false);
  }

  public void abrir() {
    setAberto(true);
  }

  public void removerFormaPagamento(FormaPagamento formaPagamento) {
    formasPagamento.remove(formaPagamento);
  }

  public void adicionarFormaPagamento(FormaPagamento formaPagamento) {
    formasPagamento.add(formaPagamento);
  }

  public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
    return getFormasPagamento().contains(formaPagamento);
  }

  public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
    return !aceitaFormaPagamento(formaPagamento);
  }

  public void associarUsuario(Usuario usuario) {
    usuarios.add(usuario);
  }

  public void desasociarUsuario(Usuario usuario) {
    usuarios.remove(usuario);
  }
}
