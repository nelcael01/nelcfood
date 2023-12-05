package com.nelcfood.service;

import com.nelcfood.model.entities.FormaPagamento;
import com.nelcfood.model.entities.Produto;
import com.nelcfood.model.exception.naoEncontrada.RestauranteNaoEncontradoException;
import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.model.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RestauranteService {

  RestauranteRepository restauranteRepository;
  FormaPagamentoService formaPagamentoService;
  CozinhaService cozinhaService;
  CidadeService cidadeService;

  public List<Restaurante> listar() {
    return restauranteRepository.findAll();
  }

  public Restaurante buscar(Long id) {
    return restauranteRepository.findById(id).orElseThrow(
            () -> new RestauranteNaoEncontradoException());
  }

  @Transactional
  public void ativar(Long id) {
    Restaurante restauranteAtual = buscar(id);
    restauranteAtual.ativar();
  }

  @Transactional
  public void inativar(Long id) {
    Restaurante restauranteBuscado = buscar(id);
    restauranteBuscado.inativar();
  }

  @Transactional
  public Restaurante salvar(Restaurante restaurante) {
    restaurante.setCozinha(cozinhaService.buscarPorId(restaurante.getCozinha().getId()));
    restaurante.getEndereco().setCidade(cidadeService.buscar(restaurante.getEndereco().getCidade().getId()));
    return restauranteRepository.save(restaurante);
  }

  @Transactional
  public void desasociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
    Restaurante restauranteBuscado = buscar(restauranteId);
    FormaPagamento formaPagamentoBuscada = formaPagamentoService.buscar(formaPagamentoId);
    restauranteBuscado.removerFormaPagamento(formaPagamentoBuscada);
    salvar(restauranteBuscado);
  }

  @Transactional
  public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
    Restaurante restauranteBuscado = buscar(restauranteId);
    FormaPagamento formaPagamentoBuscada = formaPagamentoService.buscar(formaPagamentoId);
    restauranteBuscado.adicionarFormaPagamento(formaPagamentoBuscada);
    salvar(restauranteBuscado);
  }

  @Transactional
  public void abrir(Long id) {
    Restaurante restauranteAtual = buscar(id);
    restauranteAtual.abrir();
  }

  @Transactional
  public void fechar(Long id) {
    Restaurante restauranteBuscado = buscar(id);
    restauranteBuscado.fechar();
  }

}
