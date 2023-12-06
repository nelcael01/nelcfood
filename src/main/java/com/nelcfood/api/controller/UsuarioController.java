package com.nelcfood.api.controller;

import com.nelcfood.api.dto.request.UsuarioAtualizarSenhaDTORequest;
import com.nelcfood.api.dto.request.UsuarioDTORequest;
import com.nelcfood.api.dto.request.UsuarioSemSenhaDTORequest;
import com.nelcfood.api.dto.response.UsuarioDTOResponse;
import com.nelcfood.api.transformar.request.UsuarioRequestDesmontar;
import com.nelcfood.api.transformar.request.UsuarioSemSenhaRequestDesmontar;
import com.nelcfood.api.transformar.response.UsuarioResponseMontar;
import com.nelcfood.model.entities.Usuario;
import com.nelcfood.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

  UsuarioService service;

  UsuarioResponseMontar usuarioResponseMontar;

  UsuarioRequestDesmontar usuarioRequestDesmontar;

  UsuarioSemSenhaRequestDesmontar usuarioSemSenhaRequestDesmontar;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<UsuarioDTOResponse> listar() {
    return usuarioResponseMontar.transformarColecaoEmResponse(service.listar());
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{id}")
  public UsuarioDTOResponse buscar(@PathVariable Long id) {
    System.out.println("SERVIDOOOOO" + service.buscar(id));
    return usuarioResponseMontar.transformarEntidadeEmResponse(service.buscar(id));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public UsuarioDTOResponse salvar(@RequestBody @Valid UsuarioDTORequest usuarioDTORequest) {
    Usuario usuarioSalvo = service.salvar(usuarioRequestDesmontar.transformarRequestEmEntidade(usuarioDTORequest));
    return usuarioResponseMontar.transformarEntidadeEmResponse(usuarioSalvo);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{id}")
  public UsuarioDTOResponse atualizar
          (@PathVariable Long id, @RequestBody @Valid UsuarioSemSenhaDTORequest usuarioSemSenhaDTORequest) {
    Usuario usuarioAtual = service.buscar(id);
    usuarioSemSenhaRequestDesmontar.copiarRequestParaEntidade(usuarioSemSenhaDTORequest, usuarioAtual);
    return usuarioResponseMontar.transformarEntidadeEmResponse(service.salvar(usuarioAtual));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void deletar(@PathVariable Long id) {
    service.excluir(id);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{id}/senha")
  public void atualizaSenha(@PathVariable Long id, @RequestBody @Valid UsuarioAtualizarSenhaDTORequest usuarioAtualizarSenhaDTORequest) {
    service.atualizarSenha(id, usuarioAtualizarSenhaDTORequest);
  }

}
