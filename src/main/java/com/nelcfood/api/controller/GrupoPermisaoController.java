package com.nelcfood.api.controller;

import com.nelcfood.api.dto.response.PermissaoDTOResponse;
import com.nelcfood.api.transformar.response.PermissaoResponseMontar;
import com.nelcfood.service.GrupoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
@AllArgsConstructor
public class GrupoPermisaoController {

  GrupoService grupoService;
  PermissaoResponseMontar permissaoResponseMontar;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<PermissaoDTOResponse> listar(@PathVariable Long grupoId) {
    return permissaoResponseMontar.transformarColecaoEmResponse(grupoService.listarPermissoes(grupoId));
  }

  @PutMapping("{permissaoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
    grupoService.associar(permissaoId, grupoId);
  }
}
