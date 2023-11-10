package com.nelcfood.service;

import com.nelcfood.model.exception.EntidadeEmUsoException;
import com.nelcfood.model.exception.naoEncontrada.RestauranteNaoEncontradoException;
import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.model.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

@AllArgsConstructor
@Service
public class RestauranteService {

  RestauranteRepository restauranteRepository;
  CozinhaService cozinhaService;

  public List<Restaurante> listar() {
    return restauranteRepository.findAll();
  }

  public Restaurante buscar(Long id) {
    return restauranteRepository.findById(id).orElseThrow(
            () -> new RestauranteNaoEncontradoException());
  }

  @Transactional
  public Restaurante salvar(Restaurante restaurante) {
    restaurante.setCozinha(cozinhaService.buscarPorId(restaurante.getCozinha().getId()));
    return restauranteRepository.save(restaurante);
  }

}
