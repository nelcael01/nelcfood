package com.nelcfood.nelcfood.service;

import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.model.exception.EntidadeEmUsoException;
import com.nelcfood.model.exception.naoEncontrada.CozinhaNaoEncontradaException;
import com.nelcfood.model.exception.naoEncontrada.EntidadeNaoEncontradaException;
import com.nelcfood.service.CozinhaService;
import com.nelcfood.service.RestauranteService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CozinhaServiceTest {

  @Autowired
  CozinhaService cozinhaService;

  @Autowired
  RestauranteService restauranteService;

  @Test
  public void CadastrarCozinha() {
    Cozinha novaCozinha = Cozinha.builder().nome("Argentina").build();
    Cozinha cozinhaSalva = cozinhaService.salvar(novaCozinha);
    Assert.assertNotNull(cozinhaSalva.getId());
  }

  @Test
  public void falharAoCadastrarCozinhaSemNome() {
    Cozinha novaCozinha = Cozinha.builder().nome(null).build();

    ConstraintViolationException erroEsperado =
            Assertions.assertThrows(ConstraintViolationException.class, () -> {
              cozinhaService.salvar(novaCozinha);
            });

    Assert.assertNotNull(erroEsperado);
  }

  @Test
  public void falharAoExcluirCozinhaEmUso() {
    Cozinha cozinhaCriada = Cozinha.builder().nome("Primeira cozinha").id(1L).build();
    cozinhaService.salvar(cozinhaCriada);
    Restaurante restauranteCriado = Restaurante.builder().cozinha(cozinhaCriada).nome("Restaurante teste").taxaFrete(new BigDecimal(11)).build();
    restauranteService.salvar(restauranteCriado);

    EntidadeEmUsoException erroEsperado =
            Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
              cozinhaService.deletar(1L);
            });

    Assert.assertNotNull(erroEsperado);
  }

  @Test
  public void falharAoExcluirCozinhaInexistente() {
    CozinhaNaoEncontradaException erroEsperado =
            Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
              cozinhaService.deletar(100L);
            });
    Assert.assertNotNull(erroEsperado);
  }
}
