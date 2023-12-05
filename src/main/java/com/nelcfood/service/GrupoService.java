package com.nelcfood.service;

import com.nelcfood.model.entities.Grupo;
import com.nelcfood.model.entities.Permissao;
import com.nelcfood.model.exception.EntidadeEmUsoException;
import com.nelcfood.model.exception.naoEncontrada.GrupoNaoEncontradoException;
import com.nelcfood.model.repository.GrupoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GrupoService {

  GrupoRepository grupoRepository;
  PermissaoService permissaoService;

  public List<Grupo> listar() {
    return grupoRepository.findAll();
  }

  public Grupo buscar(Long id) {
    return grupoRepository.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException());
  }

  public Grupo salvar(Grupo grupo) {
    return grupoRepository.save(grupo);
  }

  public void excluir(Long id) {
    try {
      buscar(id);
      grupoRepository.deleteById(id);
      grupoRepository.flush();
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException("O Grupo não pode ser excluido por ter relação com alguma outra entidade");
    }
  }

  public List<Permissao> listarPermissoes(Long grupoId) {
    Grupo grupoBuscado = buscar(grupoId);
    return grupoBuscado.getPermissoes();
  }

  public void associar(Long permissaoId, Long grupoId) {
    Grupo grupoBuscado = buscar(grupoId);
    Permissao permissaoBuscada = permissaoService.buscar(permissaoId);
    grupoBuscado.associar(permissaoBuscada);
    salvar(grupoBuscado);
  }



}
