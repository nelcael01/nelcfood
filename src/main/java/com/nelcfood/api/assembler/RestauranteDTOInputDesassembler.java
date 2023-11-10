package com.nelcfood.api.assembler;

import com.nelcfood.api.dto.input.RestauranteDTOInput;
import com.nelcfood.model.entities.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDTOInputDesassembler {

  @Autowired
  private ModelMapper modelMapper;

  public Restaurante paraEntidade(RestauranteDTOInput restauranteDTOInput) {
    return modelMapper.map(restauranteDTOInput, Restaurante.class);
  }
}
