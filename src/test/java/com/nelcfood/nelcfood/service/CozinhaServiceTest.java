package com.nelcfood.nelcfood.service;

import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.service.CozinhaService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CozinhaServiceTest {

  @Autowired
  CozinhaService cozinhaService;

  @Test
  public void CadastrarCozinha() {
    Cozinha novaCozinha = Cozinha.builder().nome("Argentina").build();
    Cozinha cozinhaSalva = cozinhaService.salvar(novaCozinha);
    Assert.assertNotNull(cozinhaSalva.getId());
  }

  @Test(expected = ConstraintViolationException.class)
  public void falharAoCadastrarCozinhaSemNome() {
    Cozinha novaCozinha = Cozinha.builder().nome(null).build();
    Cozinha cozinhaSalva = cozinhaService.salvar(novaCozinha);
  }
}
