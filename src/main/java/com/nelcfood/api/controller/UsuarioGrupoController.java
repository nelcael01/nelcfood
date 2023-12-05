package com.nelcfood.api.controller;

import com.nelcfood.api.dto.response.GrupoDTOResponse;
import com.nelcfood.api.transformar.response.GrupoResponseMontar;
import com.nelcfood.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
@AllArgsConstructor
public class UsuarioGrupoController {

  private UsuarioService usuarioService;
  private GrupoResponseMontar grupoResponseMontar;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Collection<GrupoDTOResponse> listar(@PathVariable Long usuarioId) {
    return grupoResponseMontar.tranformarColecaoEmResponse(usuarioService.listarGrupos(usuarioId));
  }

  @PutMapping("/{grupoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
    usuarioService.associar(usuarioId, grupoId);
  }

  @DeleteMapping("/{grupoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void desasociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
    usuarioService.desasociar(usuarioId, grupoId);
  }

}
