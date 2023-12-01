package com.nelcfood.service;

import com.nelcfood.model.entities.Produto;
import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.model.exception.naoEncontrada.ProdutoNaoEncontradoException;
import com.nelcfood.model.exception.semRelacao.ProdutoSemRelacionamentoComRestauranteException;
import com.nelcfood.model.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

  RestauranteService restauranteService;
  ProdutoRepository produtoRepository;
  ModelMapper modelMapper;

  public List<Produto> listar(Long restauranteId) {
    Restaurante restauranteBuscado = restauranteService.buscar(restauranteId);
    return restauranteBuscado.getProdutos();
  }

  public Produto buscar(Long restaurenteId, Long produtoId) {
    Restaurante restauranteBuscado = restauranteService.buscar(restaurenteId);
    Produto produtoBuscado = produtoRepository.findById(produtoId).orElseThrow(
            () -> new ProdutoNaoEncontradoException());
    Produto produtoEncontrado = restauranteBuscado.getProdutos().stream()
            .filter((produto) -> produto.getId() == produtoBuscado.getId())
            .findFirst().orElseThrow(() -> new ProdutoSemRelacionamentoComRestauranteException());
    return produtoEncontrado;
  }

  @Transactional
  public Produto salvar(Produto produto, Long restauranteId) {
    produto.setRestaurante(restauranteService.buscar(restauranteId));
    return produtoRepository.save(produto);
  }

}
