package com.nelcfood.api.assembler;

import com.nelcfood.api.dto.exit.CozinhaDTOExit;
import com.nelcfood.api.dto.exit.RestauranteDTOExit;
import com.nelcfood.model.entities.Restaurante;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteDTOAssembler {
  public RestauranteDTOExit paraDTO(Restaurante restaurante) {
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

  public List<RestauranteDTOExit> paraColecaoDeDTO(List<Restaurante> restaurentes) {
    return restaurentes.stream()
            .map((restaurante) -> paraDTO(restaurante))
            .collect(Collectors.toList());
  }
}
