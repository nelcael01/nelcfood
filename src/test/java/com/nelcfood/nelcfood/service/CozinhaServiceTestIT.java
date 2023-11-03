package com.nelcfood.nelcfood.service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CozinhaServiceTestIT {

  @LocalServerPort
  private int port;

  @Test
  public void deveRetonar200QuandoListarCozinhas() {
    RestAssured
            .given()
            .basePath("/cozinhas")
            .port(port)
            .accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .statusCode(200);
  }
}
