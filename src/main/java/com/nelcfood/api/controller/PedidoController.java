package com.nelcfood.api.controller;

import com.nelcfood.api.dto.response.PedidoDTOResponse;
import com.nelcfood.api.dto.response.PedidoResumoDTOResponse;
import com.nelcfood.api.transformar.response.PedidoResponseMontar;
import com.nelcfood.api.transformar.response.PedidoResumoResponseMontar;
import com.nelcfood.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {

  PedidoService pedidoService;

  PedidoResponseMontar pedidoResponseMontar;
  PedidoResumoResponseMontar pedidoResumoResponseMontar;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<PedidoResumoDTOResponse> listar() {
    return pedidoResumoResponseMontar.transformarEntidadeEmColecao(pedidoService.listar());
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public PedidoDTOResponse buscar(@PathVariable Long id) {
    return pedidoResponseMontar.transformarEntidadeEmResponse(pedidoService.buscar(id));
  }
}
