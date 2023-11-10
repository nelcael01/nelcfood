package com.nelcfood.api.controller;

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

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<RestauranteDTOExit> listar() {
    return paraColecaoDeDTO(restauranteService.listar());
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RestauranteDTOExit buscar(@PathVariable Long id) {
    return paraDTO(restauranteService.buscar(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RestauranteDTOExit salvar(@RequestBody @Valid RestauranteDTOInput restaurante) {
    Restaurante restauranteASerSalvo = paraEntidade(restaurante);
    try {
      return paraDTO(restauranteService.salvar(restauranteASerSalvo));
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
      return paraDTO(restauranteService.salvar(restauranteBuscado));
    } catch (CozinhaNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }


  private RestauranteDTOExit paraDTO(Restaurante restaurante) {
    CozinhaDTOExit cozinha = CozinhaDTOExit
            .builder()
            .id(restaurante.getCozinha().getId())
            .nome(restaurante.getCozinha().getNome())
            .build();

    RestauranteDTOExit restauranteDTO = RestauranteDTOExit
            .builder()
            .id(restaurante.getId())
            .nome(restaurante.getNome())
            .taxaFrete(restaurante.getTaxaFrete())
            .cozinha(cozinha)
            .build();

    return restauranteDTO;
  }

  private List<RestauranteDTOExit> paraColecaoDeDTO(List<Restaurante> restaurentes) {
    return restaurentes.stream()
            .map((restaurante) -> paraDTO(restaurante))
            .collect(Collectors.toList());
  }

  private Restaurante paraEntidade(RestauranteDTOInput restauranteDTOInput) {
    Cozinha cozinha = Cozinha
            .builder()
            .id(restauranteDTOInput.getCozinha().getId())
            .nome(restauranteDTOInput.getNome())
            .build();

    return Restaurante
            .builder()
            .nome(restauranteDTOInput.getNome())
            .taxaFrete(restauranteDTOInput.getTaxaFrete())
            .cozinha(cozinha)
            .build();
  }

  private List<Restaurante> paraColecaoDeEntidade(List<RestauranteDTOInput> restaurantesDTOInputs) {
    return restaurantesDTOInputs.stream()
            .map(restauranteDTOInput -> paraEntidade(restauranteDTOInput))
            .collect(Collectors.toList());
  }
}

