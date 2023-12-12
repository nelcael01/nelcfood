package com.nelcfood.service;

import com.nelcfood.model.entities.Pedido;
import com.nelcfood.model.exception.naoEncontrada.PedidoNaoEncontradoException;
import com.nelcfood.model.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {
  private PedidoRepository pedidoRepository;

  public List<Pedido> listar() {
    return pedidoRepository.findAll();
  }

  public Pedido buscar(Long id) {
    return pedidoRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradoException());
  }

}
