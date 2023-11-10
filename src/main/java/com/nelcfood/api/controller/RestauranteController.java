package com.nelcfood.api.controller;

import com.nelcfood.api.assembler.RestauranteDTOAssembler;
import com.nelcfood.api.assembler.RestauranteDTODesassembler;
import com.nelcfood.api.dto.exit.CozinhaDTOExit;
import com.nelcfood.api.dto.exit.RestauranteDTOExit;
import com.nelcfood.api.dto.input.CozinhaIdDTOInput;
import com.nelcfood.api.dto.input.RestauranteDTOInput;
import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.model.exception.NegocioException;
import com.nelcfood.model.exception.naoEncontrada.CozinhaNaoEncontradaException;
import com.nelcfood.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

  RestauranteService restauranteService;
  RestauranteDTOAssembler restauranteDTOAssembler;
  RestauranteDTODesassembler restauranteDTODesassembler;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<RestauranteDTOExit> listar() {
    return restauranteDTOAssembler.paraColecaoDeDTO(restauranteService.listar());
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RestauranteDTOExit buscar(@PathVariable Long id) {
    return restauranteDTOAssembler.paraDTO(restauranteService.buscar(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RestauranteDTOExit salvar(@RequestBody @Valid RestauranteDTOInput restaurante) {
    Restaurante restauranteASerSalvo = restauranteDTODesassembler.paraEntidade(restaurante);
    try {
      return restauranteDTOAssembler.paraDTO(restauranteService.salvar(restauranteASerSalvo));
    } catch (CozinhaNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RestauranteDTOExit atualizar(@RequestBody @Valid RestauranteDTOInput restaurante, @PathVariable Long id) {
    Restaurante restauranteBuscado = restauranteService.buscar(id);
    BeanUtils.copyProperties(restaurante, restauranteBuscado, "id");
    try {
      return restauranteDTOAssembler.paraDTO(restauranteService.salvar(restauranteBuscado));
    } catch (CozinhaNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }

}

