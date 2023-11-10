package com.nelcfood.api.assembler;

import com.nelcfood.api.dto.input.RestauranteDTOInput;
import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.model.entities.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDTODesassembler {


  public Restaurante paraEntidade(RestauranteDTOInput restauranteDTOInput) {
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
}
