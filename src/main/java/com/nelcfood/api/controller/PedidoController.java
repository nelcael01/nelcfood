package com.nelcfood.api.controller;

import com.nelcfood.api.dto.request.PedidoDTORequest;
import com.nelcfood.api.dto.response.PedidoDTOResponse;
import com.nelcfood.api.dto.response.PedidoResumoDTOResponse;
import com.nelcfood.api.transformar.request.PedidoResumoRequestDesmontar;
import com.nelcfood.api.transformar.response.PedidoResponseMontar;
import com.nelcfood.api.transformar.response.PedidoResumoResponseMontar;
import com.nelcfood.model.entities.Pedido;
import com.nelcfood.model.entities.Usuario;
import com.nelcfood.model.exception.NegocioException;
import com.nelcfood.model.exception.naoEncontrada.EntidadeNaoEncontradaException;
import com.nelcfood.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {

  PedidoService pedidoService;

  PedidoResponseMontar pedidoResponseMontar;
  PedidoResumoResponseMontar pedidoResumoResponseMontar;
  PedidoResumoRequestDesmontar pedidoResumoRequestDesmontar;


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

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PedidoDTOResponse salvar(@RequestBody @Valid PedidoDTORequest pedidoDTORequest) {
    try {
      Pedido novoPedido = pedidoResumoRequestDesmontar.transformarRequestEmEntity(pedidoDTORequest);
      novoPedido.setCliente(new Usuario());
      novoPedido.getCliente().setId(1L);
      return pedidoResponseMontar.transformarEntidadeEmResponse(pedidoService.salvar(novoPedido));
    } catch (EntidadeNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }

  @PutMapping("/{id}/confirmacao")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void confirmar(@PathVariable Long id) {
    pedidoService.confimar(id);
  }

  @PutMapping("/{id}/cancelamento")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void cancelar(@PathVariable Long id) {
    pedidoService.cancelar(id);
  }

  @PutMapping("/{id}/entrega")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void entregar(@PathVariable Long id) {
    pedidoService.entregar(id);
  }
}
