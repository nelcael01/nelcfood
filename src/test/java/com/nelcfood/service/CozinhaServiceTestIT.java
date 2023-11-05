package com.nelcfood.service;

import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.model.repository.CozinhaRepository;
import com.nelcfood.util.DatabaseCleaner;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CozinhaServiceTestIT {

  @LocalServerPort
  private int port;

  @Autowired
  private DatabaseCleaner databaseCleaner;

  @Autowired
  private CozinhaRepository cozinhaRepository;

  @Before
  public void setUp() {
    RestAssured.basePath = "/cozinhas";
    RestAssured.port = port;
    databaseCleaner.clearTables();
    prepararDados();
  }

  @Test
  public void deveRetonar200QuandoListarCozinhas() {
    RestAssured.given()
            .accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .statusCode(HttpStatus.OK.value());
  }

  @Test
  public void deveConter3CozinhasQuandoConsultarCozinhas() {
    RestAssured.given()
            .accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .body("", Matchers.hasSize(3));
  }

  @Test
  public void deveRetonar200ERespostaCorretaQuandoConsultarCozinhaExistente() {
    RestAssured.given()
            .pathParams("id", 1)
            .accept(ContentType.JSON)
            .when()
            .get("/{id}")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", Matchers.equalTo("Chinesa Teste"));
  }

  @Test
  public void deveRetonar404QuandoConsultarCozinhaInexistente() {
    RestAssured.given()
            .pathParams("id", 10)
            .accept(ContentType.JSON)
            .when()
            .get("/{id}")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
  }

  @Test
  public void deveRetornar201QuandoCadastrarCozinha() {
    RestAssured.given()
            .body("{ \"nome\": \"Chinesa\" }")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.CREATED.value());
  }

  private void prepararDados() {
    Cozinha cozinha1 = Cozinha.builder().nome("Chinesa Teste").build();
    cozinhaRepository.save(cozinha1);

    Cozinha cozinha2 = Cozinha.builder().nome("Indiana Teste").build();
    cozinhaRepository.save(cozinha2);

    Cozinha cozinha3 = Cozinha.builder().nome("Brasileira Teste").build();
    cozinhaRepository.save(cozinha3);
  }
}
