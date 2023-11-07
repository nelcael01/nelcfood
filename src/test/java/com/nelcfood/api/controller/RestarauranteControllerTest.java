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
public class RestarauranteControllerTest {

  public static final int ID_INEXISTENTE = 1000;
  private int contadorRestaurantes;
  private Restaurante restauranteValidacao;
  private String jsonDeveRetonar201_QuandoSalvarRestaurante;
  private String jsonDeveRetonar400_QuandoSalvarRestauranteComCozinhaInvalida;
  private String jsonDeveRetonar400_QuandoSalvarRestauranteComNomeInvalido;
  private String jsonDeveRetonar400_QuandoSalvarRestauranteComFreteInvalido;

  @LocalServerPort
  private int port;

  @Autowired
  private DatabaseCleaner databaseCleaner;

  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CozinhaRepository cozinhaRepository;

  @Before
  public void setUp() {
    RestAssured.basePath = "/restaurantes";
    RestAssured.port = port;
    jsonDeveRetonar201_QuandoSalvarRestaurante = LeituraDeJson.getConteudoJson(
            "/json/deveRetonar201_QuandoSalvarRestaurante.json");

    jsonDeveRetonar400_QuandoSalvarRestauranteComCozinhaInvalida = LeituraDeJson.getConteudoJson(
            "/json/deveRetonar400_QuandoSalvarRestauranteComCozinhaInvalida");

    jsonDeveRetonar400_QuandoSalvarRestauranteComNomeInvalido = LeituraDeJson.getConteudoJson(
            "/json/deveRetonar400_QuandoSalvarRestauranteComNomeInvalido.json");

    jsonDeveRetonar400_QuandoSalvarRestauranteComFreteInvalido = LeituraDeJson.getConteudoJson(
            "/json/deveRetonar400_QuandoSalvarRestauranteComFreteInvalido.json");
    databaseCleaner.clearTables();
    prepararDados();
  }

  @Test
  public void deveRetonar200_QuandoListarRestaurentes() {
    RestAssured.given()
            .accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .statusCode(HttpStatus.OK.value());
  }

  @Test
  public void deveConterOMesmoNumeroDeRestaurantesDoContador_QuandoListarRestaurantes() {
    RestAssured.given()
            .accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .body("", Matchers.hasSize(contadorRestaurantes));
  }

  @Test
  public void deveRetonar200ERespostaCorreta_QuandoBuscarRestauranteExistente() {
    RestAssured.given()
            .pathParams("id", restauranteValidacao.getId())
            .accept(ContentType.JSON)
            .when()
            .get("{id}")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", Matchers.equalTo(restauranteValidacao.getNome()));
  }

  @Test
  public void deveRetonar404_QuandoBuscarRestauranteInexistente() {
    RestAssured.given()
            .pathParams("id", ID_INEXISTENTE)
            .accept(ContentType.JSON)
            .when()
            .get("{id}")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
  }

  @Test
  public void deveRetonar201_QuandoSalvarRestaurante() {
    RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(jsonDeveRetonar201_QuandoSalvarRestaurante)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.CREATED.value());
  }

  @Test
  public void deveRetonar400_QuandoSalvarRestauranteComCozinhaInvalida() {
    RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(jsonDeveRetonar400_QuandoSalvarRestauranteComCozinhaInvalida)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  public void deveRetonar400_QuandoSalvarRestauranteComNomeInvalido() {
    RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(jsonDeveRetonar400_QuandoSalvarRestauranteComNomeInvalido)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  public void deveRetonar400_QuandoSalvarRestauranteComFreteInvalido() {
    RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(jsonDeveRetonar400_QuandoSalvarRestauranteComFreteInvalido)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
  }


  private void prepararDados() {
    Cozinha cozinha = Cozinha.builder().nome("Brasileira").build();
    cozinhaRepository.save(cozinha);

    restauranteValidacao = Restaurante.builder()
            .nome("Restaurante Brasileiro Teste")
            .taxaFrete(BigDecimal.valueOf(10))
            .cozinha(cozinha)
            .build();
    restauranteRepository.save(restauranteValidacao);
    contadorRestaurantes++;

    Restaurante restaurante1 = Restaurante.builder()
            .nome("Restaurante Bacanizado Teste")
            .taxaFrete(BigDecimal.valueOf(8))
            .cozinha(cozinha)
            .build();
    restauranteRepository.save(restaurante1);
    contadorRestaurantes++;

    Restaurante restaurante2 = Restaurante.builder()
            .nome("Restaurante Pampas")
            .taxaFrete(BigDecimal.valueOf(15))
            .cozinha(cozinha)
            .build();
    restauranteRepository.save(restaurante2);
    contadorRestaurantes++;
  }
}
