package com.nelcfood.api.controller;

import com.nelcfood.api.assembler.RestauranteDTOResponseAssembler;
import com.nelcfood.api.assembler.RestauranteDTORequestDisassembler;
import com.nelcfood.api.dto.response.RestauranteDTOResponse;
import com.nelcfood.api.dto.request.RestauranteDTORequest;
import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.model.exception.NegocioException;
import com.nelcfood.model.exception.naoEncontrada.CozinhaNaoEncontradaException;
import com.nelcfood.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

  RestauranteService restauranteService;
  RestauranteDTOResponseAssembler restauranteDTOAssembler;
  RestauranteDTORequestDisassembler restauranteDTODisassembler;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<RestauranteDTOResponse> listar() {
    return restauranteDTOAssembler.transformarColecaoDeEntidadeparaColecaoDeResponse(restauranteService.listar());
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RestauranteDTOResponse buscar(@PathVariable Long id) {
    return restauranteDTOAssembler.tranformarEntidadeParaResponse(restauranteService.buscar(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RestauranteDTOResponse salvar(@RequestBody @Valid RestauranteDTORequest restaurante) {
    Restaurante restauranteASerSalvo = restauranteDTODisassembler.transformarRequestEmEntidade(restaurante);
    try {
      return restauranteDTOAssembler.tranformarEntidadeParaResponse(restauranteService.salvar(restauranteASerSalvo));
    } catch (CozinhaNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RestauranteDTOResponse atualizar(
          @RequestBody @Valid RestauranteDTORequest restauranteDTORequest, @PathVariable Long id) {
    Restaurante restauranteAtual = restauranteService.buscar(id);
    restauranteDTODisassembler.copiarRequestParaEntidade(restauranteDTORequest, restauranteAtual);
    try {
      return restauranteDTOAssembler.tranformarEntidadeParaResponse(restauranteService.salvar(restauranteAtual));
    } catch (CozinhaNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }

}

