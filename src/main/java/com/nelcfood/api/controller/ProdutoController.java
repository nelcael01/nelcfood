package com.nelcfood.api.controller;

import com.nelcfood.api.dto.request.ProdutoDTORequest;
import com.nelcfood.api.dto.response.ProdutoDTOResponse;
import com.nelcfood.api.transformar.request.ProdutoRequestDesmontar;
import com.nelcfood.api.transformar.response.ProdutoResponseMontar;
import com.nelcfood.model.entities.Produto;
import com.nelcfood.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
@AllArgsConstructor
public class ProdutoController {

  ProdutoService service;

  ProdutoResponseMontar produtoResponseMontar;

  ProdutoRequestDesmontar produtoRequestDesmontar;

  @GetMapping
  public List<ProdutoDTOResponse> listar(@PathVariable Long restauranteId) {
    return produtoResponseMontar.transformarColecaoEmResponse(service.listar(restauranteId));
  }

  @GetMapping("/{produtoId}")
  public ProdutoDTOResponse buscar(@PathVariable Long produtoId, @PathVariable Long restauranteId) {
    return produtoResponseMontar.transformarEntidadeEmResponse(service.buscar(restauranteId, produtoId));
  }

  @PostMapping
  public ProdutoDTOResponse salvar(@PathVariable Long restauranteId,
                                   @RequestBody @Valid ProdutoDTORequest produtoDTORequest) {
    return produtoResponseMontar.transformarEntidadeEmResponse(
            service.salvar(produtoRequestDesmontar.transformarRequestEmEntidade(produtoDTORequest), restauranteId));
  }

  @PutMapping("/{produtoId}")
  public ProdutoDTOResponse atualizar(@RequestBody @Valid ProdutoDTORequest produtoDTORequest,
                                      @PathVariable Long restauranteId, @PathVariable Long produtoId) {
    Produto produtoEncontrado = service.buscar(restauranteId, produtoId);
    produtoRequestDesmontar.copiarRequestParaEntidade(produtoDTORequest, produtoEncontrado);
    return produtoResponseMontar.transformarEntidadeEmResponse(service.salvar(produtoEncontrado, restauranteId));
  }

}
