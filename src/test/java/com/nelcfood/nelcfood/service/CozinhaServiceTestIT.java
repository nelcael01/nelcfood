package com.nelcfood.nelcfood.service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CozinhaServiceTestIT {

  @LocalServerPort
  private int port;

  @Autowired
  private Flyway flyway;

  @Before
  public void setUp() {
    RestAssured.basePath = "/cozinhas";
    RestAssured.port = port;
    flyway.migrate();
  }

  @Test
  public void deveRetonar200QuandoListarCozinhas() {
    RestAssured
            .given()
            .accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .statusCode(HttpStatus.OK.value());
  }

  @Test
  public void deveRetornar201QuandoCadastrarCozinha() {
    RestAssured
            .given()
            .body("{ \"nome\": \"Chinesa\" }")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.CREATED.value());
  }
}
