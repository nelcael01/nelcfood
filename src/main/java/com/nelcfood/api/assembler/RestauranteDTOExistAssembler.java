package com.nelcfood.api.assembler;

import com.nelcfood.api.dto.exit.CozinhaDTOExit;
import com.nelcfood.api.dto.exit.RestauranteDTOExit;
import com.nelcfood.model.entities.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteDTOExistAssembler {

  @Autowired
  private ModelMapper modelMapper;

  public RestauranteDTOExit paraDTO(Restaurante restaurante) {
    return modelMapper.map(restaurante, RestauranteDTOExit.class);
  }

  public List<RestauranteDTOExit> paraColecaoDeDTO(List<Restaurante> restaurentes) {
    return restaurentes.stream()
            .map((restaurante) -> paraDTO(restaurante))
            .collect(Collectors.toList());
  }
}
