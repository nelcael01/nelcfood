package com.nelcfood.api.transformar.response;

import com.nelcfood.api.dto.response.RestauranteDTOResponse;
import com.nelcfood.model.entities.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteResponseMontar {

  @Autowired
  private ModelMapper modelMapper;

  public RestauranteDTOResponse tranformarEntidadeParaResponse(Restaurante restaurante) {
    return modelMapper.map(restaurante, RestauranteDTOResponse.class);
  }

  public List<RestauranteDTOResponse> transformarColecaoDeEntidadeparaColecaoDeResponse(Collection<Restaurante> restaurentes) {
    return restaurentes.stream()
            .map((restaurante) -> tranformarEntidadeParaResponse(restaurante))
            .collect(Collectors.toList());
  }
}
