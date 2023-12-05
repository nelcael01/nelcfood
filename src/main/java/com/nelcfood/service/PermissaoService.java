package com.nelcfood.service;

import com.nelcfood.model.entities.Permissao;
import com.nelcfood.model.exception.naoEncontrada.PermissaoNaoEncontradaException;
import com.nelcfood.model.repository.PermissaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PermissaoService {

  PermissaoRepository repository;

  public Permissao buscar(Long id) {
    return repository.findById(id).orElseThrow(() -> new PermissaoNaoEncontradaException());
  }
}
