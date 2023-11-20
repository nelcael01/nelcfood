package com.nelcfood.core.modelMapper;

import com.nelcfood.api.dto.response.EnderecoDTOResponse;
import com.nelcfood.model.entities.Endereco;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
  @Bean
  public ModelMapper modelMapper() {
    var modelMapper = new ModelMapper();

    var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoDTOResponse.class);
    enderecoToEnderecoModelTypeMap.<String>addMapping
            (src -> src.getCidade().getEstado().getNome(), (dest, value) -> dest.getCidade().setEstado(value));

    return modelMapper;
  }
}
