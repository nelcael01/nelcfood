package com.nelcfood.api.controller;

import com.nelcfood.api.dto.response.UsuarioDTOResponse;
import com.nelcfood.api.transformar.response.RestauranteResponseMontar;
import com.nelcfood.api.transformar.response.UsuarioResponseMontar;
import com.nelcfood.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
@AllArgsConstructor
public class RestauranteUsuarioResponsavelController {

  RestauranteService restauranteService;
  RestauranteResponseMontar restauranteResponseMontar;
  UsuarioResponseMontar usuarioResponseMontar;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<UsuarioDTOResponse> listarUsuario(@PathVariable Long restauranteId) {
    return usuarioResponseMontar.transformarColecaoEmResponse(restauranteService.listarUsuarios(restauranteId));
  }

  @PutMapping("/{usuarioId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
    restauranteService.associarUsuario(restauranteId, usuarioId);
  }

  @DeleteMapping("/{usuarioId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void desasociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
    restauranteService.desasociarUsuario(restauranteId, usuarioId);
  }
}
