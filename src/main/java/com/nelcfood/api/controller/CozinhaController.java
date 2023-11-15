package com.nelcfood.api.controller;

import com.nelcfood.api.assembler.CozinhaRequestDisassembler;
import com.nelcfood.api.assembler.CozinhaResponseAssembler;
import com.nelcfood.api.dto.request.CozinhaDTORequest;
import com.nelcfood.api.dto.response.CozinhaDTOResponse;
import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.service.CozinhaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

  CozinhaService cozinhaService;
  CozinhaResponseAssembler cozinhaResponseAssembler;
  CozinhaRequestDisassembler cozinhaRequestDisassembler;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<CozinhaDTOResponse> listar() {
    return cozinhaResponseAssembler.transformarColecaoEmResponse(cozinhaService.listar());
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public CozinhaDTOResponse buscarPorId(@PathVariable Long id) {
    return cozinhaResponseAssembler.tranformarEntidadeEmResponse(cozinhaService.buscarPorId(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CozinhaDTOResponse salvar(@RequestBody @Valid CozinhaDTORequest cozinha) {
    return cozinhaResponseAssembler.tranformarEntidadeEmResponse
            (cozinhaService.salvar(cozinhaRequestDisassembler.transformarRequestEmEntidade(cozinha)));
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CozinhaDTOResponse atualizar(@PathVariable Long id, @RequestBody @Valid CozinhaDTORequest cozinha) {
    Cozinha cozinhaAtual = cozinhaService.buscarPorId(id);
    cozinhaRequestDisassembler.copiarRequestParaEntidade(cozinha, cozinhaAtual);
    return cozinhaResponseAssembler.tranformarEntidadeEmResponse(cozinhaService.salvar(cozinhaAtual));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletar(@PathVariable Long id) {
    cozinhaService.deletar(id);
  }
}
