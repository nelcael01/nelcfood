package com.nelcfood.api.controller;

import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.model.repository.CozinhaRepository;
import com.nelcfood.model.repository.RestauranteRepository;
import com.nelcfood.util.DatabaseCleaner;
import com.nelcfood.util.LeituraDeJson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CozinhaControllerTest {
  public static final int ID_INEXISTENTE = 1000;
  private int contadorCozinhas;
  private Cozinha cozinhaValidacao;

  private Cozinha cozinhaConflit;

  private String jsonDeveRetornar201_QuandoSalvarCozinha;

  private String jsonDeveRetonar400_QuandoSalvarCozinhaComNomeVazio;

  @LocalServerPort
  private int port;

  @Autowired
  private DatabaseCleaner databaseCleaner;

  @Autowired
  private CozinhaRepository cozinhaRepository;

  @Autowired
  private RestauranteRepository restauranteRepository;


  @Before
  public void setUp() {
    RestAssured.basePath = "/cozinhas";
    RestAssured.port = port;
    databaseCleaner.clearTables();

    jsonDeveRetornar201_QuandoSalvarCozinha = LeituraDeJson.getConteudoJson(
            "/json/deveRetornar201_QuandoSalvarCozinha.json");

    jsonDeveRetonar400_QuandoSalvarCozinhaComNomeVazio = LeituraDeJson.getConteudoJson(
            "/json/jsonDeveRetonar400_QuandoSalvarCozinhaComNomeVazio.json");

    prepararDados();
  }

  @Test
  public void deveRetonar200_QuandoListarCozinhas() {
    RestAssured.given()
            .accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .statusCode(HttpStatus.OK.value());
  }

  @Test
  public void deveConterOMesmoNumeroDeRestaurantesDoContador_QuandoListarCozinhas() {
    RestAssured.given()
            .accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .body("", Matchers.hasSize(contadorCozinhas));
  }

  @Test
  public void deveRetonar200ERespostaCorreta_QuandoBuscarCozinhaExistente() {
    RestAssured.given()
            .pathParams("id", cozinhaValidacao.getId())
            .accept(ContentType.JSON)
            .when()
            .get("/{id}")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", Matchers.equalTo(cozinhaValidacao.getNome()));
  }

  @Test
  public void deveRetonar404_QuandoBuscarCozinhaInexistente() {
    RestAssured.given()
            .pathParams("id", ID_INEXISTENTE)
            .accept(ContentType.JSON)
            .when()
            .get("/{id}")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
  }

  @Test
  public void deveRetornar201_QuandoSalvarCozinha() {
    RestAssured.given()
            .body(jsonDeveRetornar201_QuandoSalvarCozinha)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.CREATED.value());
  }

  @Test
  public void deveRetonar400_QuandoSalvarCozinhaComNomeVazio() {
    RestAssured.given()
            .body(jsonDeveRetonar400_QuandoSalvarCozinhaComNomeVazio)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  public void deveRetonar204_QuandoDeletarCozinha() {
    RestAssured.given()
            .contentType(ContentType.JSON)
            .pathParams("id", cozinhaValidacao.getId())
            .when()
            .delete("{id}")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
  }

  @Test
  public void deveRetonar404_QuandoDeletarCozinhaInexistente() {
    RestAssured.given()
            .contentType(ContentType.JSON)
            .pathParams("id", ID_INEXISTENTE)
            .when()
            .delete("{id}")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
  }

  @Test
  public void deveRetonar409_QuandoDeletarCozinhaEmUso() {
    RestAssured.given()
            .contentType(ContentType.JSON)
            .pathParams("id", cozinhaConflit.getId())
            .when()
            .delete("{id}")
            .then()
            .statusCode(HttpStatus.CONFLICT.value());
  }

  private void prepararDados() {
    cozinhaValidacao = Cozinha.builder().nome("Chinesa Teste").build();
    cozinhaRepository.save(cozinhaValidacao);
    contadorCozinhas++;

    cozinhaConflit = Cozinha.builder().nome("Indiada Teste").build();
    cozinhaRepository.save(cozinhaConflit);
    Restaurante restaurante = Restaurante.builder()
            .nome("Restaurante Teste")
            .taxaFrete(BigDecimal.valueOf(10))
            .cozinha(cozinhaConflit)
            .build();
    restauranteRepository.save(restaurante);
    contadorCozinhas++;

    Cozinha cozinha2 = Cozinha.builder().nome("Brasileira Teste").build();
    cozinhaRepository.save(cozinha2);
    contadorCozinhas++;
  }
}
