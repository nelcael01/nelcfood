package com.nelcfood.api.controller;

import com.nelcfood.api.dto.response.FormaPagamentoDTOResponse;
import com.nelcfood.api.transformar.response.FormaPagamentoResponseMontar;
import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurantes/{restaurenteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

  RestauranteService restauranteService;

  FormaPagamentoResponseMontar formaPagamentoResponseMontar;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<FormaPagamentoDTOResponse> listar(@PathVariable Long restaurenteId) {
    Restaurante restauranteBuscado = restauranteService.buscar(restaurenteId);
    return formaPagamentoResponseMontar.transformarColecaoEmResponse(restauranteBuscado.getFormasPagamento());
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{formaPagamentoId}")
  public void desasociar(@PathVariable Long restaurenteId, @PathVariable Long formaPagamentoId) {
    restauranteService.desasociarFormaPagamento(restaurenteId, formaPagamentoId);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{formaPagamentoId}")
  public void associar(@PathVariable Long restaurenteId, @PathVariable Long formaPagamentoId) {
    restauranteService.associarFormaPagamento(restaurenteId, formaPagamentoId);
  }

}

