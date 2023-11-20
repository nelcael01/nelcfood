package com.nelcfood.api.controller;

import com.nelcfood.api.dto.request.FormaPagamentoDTORequest;
import com.nelcfood.api.dto.response.FormaPagamentoDTOResponse;
import com.nelcfood.api.transformar.request.FormaPagamentoRequestDesmontar;
import com.nelcfood.api.transformar.response.FormaPagamentoResponseMontar;
import com.nelcfood.model.entities.FormaPagamento;
import com.nelcfood.service.FormaPagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/forma-pagamentos")
public class FormaPagamentoController {

  FormaPagamentoRequestDesmontar formaPagamentoRequestDesmontador;

  FormaPagamentoResponseMontar formaPagamentoResponseMontador;

  FormaPagamentoService service;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<FormaPagamentoDTOResponse> listar() {
    return formaPagamentoResponseMontador.transformarColecaoEmResponse(service.listar());
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public FormaPagamentoDTOResponse buscar(@PathVariable Long id) {
    return formaPagamentoResponseMontador.transformarEntidadeEmResponse(service.buscar(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FormaPagamentoDTOResponse salvar(@RequestBody @Valid FormaPagamentoDTORequest formaPagamento) {
    FormaPagamento FormaPagamentoSalva = service.salvar(formaPagamentoRequestDesmontador.transformarRequestEmEntidade(formaPagamento));
    return formaPagamentoResponseMontador.transformarEntidadeEmResponse(FormaPagamentoSalva);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public FormaPagamentoDTOResponse atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoDTORequest formaPagamento) {
    FormaPagamento formaPagamentoAtual = service.buscar(id);
    formaPagamentoRequestDesmontador.copiarRequestParaEntidade(formaPagamento, formaPagamentoAtual);
    return formaPagamentoResponseMontador.transformarEntidadeEmResponse(service.salvar(formaPagamentoAtual));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletar(@PathVariable Long id) {
    service.excluir(id);
  }
}
