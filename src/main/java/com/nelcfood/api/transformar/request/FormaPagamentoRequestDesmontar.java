package com.nelcfood.api.transformar.request;

import com.nelcfood.api.dto.request.FormaPagamentoDTORequest;
import com.nelcfood.model.entities.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoRequestDesmontar {

  @Autowired
  ModelMapper modelMapper;

  public FormaPagamento transformarRequestEmEntidade(FormaPagamentoDTORequest formaPagamentoDTORequest) {
    return modelMapper.map(formaPagamentoDTORequest, FormaPagamento.class);
  }

  public void copiarRequestParaEntidade(FormaPagamentoDTORequest formaPagamentoDTORequest, FormaPagamento formaPagamento) {
    modelMapper.map(formaPagamentoDTORequest, formaPagamento);
  }
}
