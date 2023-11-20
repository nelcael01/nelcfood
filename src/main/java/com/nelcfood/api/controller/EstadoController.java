package com.nelcfood.api.controller;

import com.nelcfood.api.transformar.request.EstadoRequestDesmontar;
import com.nelcfood.api.transformar.response.EstadoResponseMontar;
import com.nelcfood.api.dto.request.EstadoDTORequest;
import com.nelcfood.api.dto.response.EstadoDTOResponse;
import com.nelcfood.model.entities.Estado;
import com.nelcfood.service.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/estados")
public class EstadoController {

  EstadoService estadoService;
  EstadoResponseMontar estadoResponseAssembler;
  EstadoRequestDesmontar estadoRequestDisassebler;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<EstadoDTOResponse> listar() {
    return estadoResponseAssembler.tranformarColecaoEmResponse(estadoService.listar());
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public EstadoDTOResponse buscar(@PathVariable Long id) {
    return estadoResponseAssembler.transformarEntidadeEmResponse(estadoService.buscar(id));
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public EstadoDTOResponse atualizar(@RequestBody @Valid EstadoDTORequest estado, @PathVariable Long id) {
    Estado estadoBuscado = estadoService.buscar(id);
    estadoRequestDisassebler.copiarRequestParaEntidade(estado, estadoBuscado);
    return estadoResponseAssembler.transformarEntidadeEmResponse(estadoService.salvar(estadoBuscado));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EstadoDTOResponse salvar(@RequestBody @Valid EstadoDTORequest estado) {
    return estadoResponseAssembler.transformarEntidadeEmResponse(
            estadoService.salvar(estadoRequestDisassebler.transfomarRequestEmEntidade(estado)));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletar(@PathVariable Long id) {
    estadoService.deletar(id);
  }

}
