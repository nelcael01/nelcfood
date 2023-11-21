package com.nelcfood.api.controller;

import com.nelcfood.api.dto.request.GrupoDTORequest;
import com.nelcfood.api.dto.response.GrupoDTOResponse;
import com.nelcfood.api.transformar.request.GrupoRequestDesmontar;
import com.nelcfood.api.transformar.response.GrupoResponseMontar;
import com.nelcfood.model.entities.Grupo;
import com.nelcfood.service.GrupoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/grupos")
public class GrupoController {

  GrupoService service;
  GrupoResponseMontar grupoResponseMontar;
  GrupoRequestDesmontar grupoRequestDesmontar;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<GrupoDTOResponse> listar() {
    return grupoResponseMontar.tranformarColecaoEmResponse(service.listar());
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public GrupoDTOResponse buscar(@PathVariable Long id) {
    return grupoResponseMontar.tranformarEntidadeEmResponse(service.buscar(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GrupoDTOResponse salvar(@RequestBody @Valid GrupoDTORequest grupoDTORequest) {
    Grupo grupoSalvo = service.salvar(grupoRequestDesmontar.transformarRequestEmEntidade(grupoDTORequest));
    return grupoResponseMontar.tranformarEntidadeEmResponse(grupoSalvo);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public GrupoDTOResponse atualizar(@PathVariable Long id, @RequestBody @Valid GrupoDTORequest grupoDTORequest) {
    Grupo grupoAtual = service.buscar(id);
    grupoRequestDesmontar.copiarRequestParaEntidade(grupoDTORequest, grupoAtual);
    return grupoResponseMontar.tranformarEntidadeEmResponse(service.salvar(grupoAtual));
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletar(@PathVariable Long id) {
    service.excluir(id);
  }

}
