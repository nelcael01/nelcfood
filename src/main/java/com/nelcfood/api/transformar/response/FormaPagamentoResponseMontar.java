package com.nelcfood.api.transformar.response;

import com.nelcfood.api.dto.response.FormaPagamentoDTOResponse;
import com.nelcfood.model.entities.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoResponseMontar {

  @Autowired
  ModelMapper modelMapper;

  public FormaPagamentoDTOResponse transformarEntidadeEmResponse(FormaPagamento formaPagamento) {
    return modelMapper.map(formaPagamento, FormaPagamentoDTOResponse.class);
  }

  public List<FormaPagamentoDTOResponse> transformarColecaoEmResponse(List<FormaPagamento> formaPagamentos) {
    return formaPagamentos.stream().map(
            (formaPagamento) -> transformarEntidadeEmResponse(formaPagamento)).collect(Collectors.toList());
  }
}
