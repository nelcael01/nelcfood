package com.nelcfood.service;

import com.nelcfood.model.entities.Estado;
import com.nelcfood.model.exception.EntidadeEmUsoException;
import com.nelcfood.model.exception.naoEncontrada.EstadoNaoEncontradoException;
import com.nelcfood.model.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class EstadoService {

  EstadoRepository estadoRepository;

  public List<Estado> listar() {
    return estadoRepository.findAll();
  }

  public Estado buscar(Long id) {
    return estadoRepository.findById(id).
            orElseThrow(() -> new EstadoNaoEncontradoException());
  }

  @Transactional
  public Estado salvar(Estado estado) {
    return estadoRepository.save(estado);
  }

  @Transactional
  public void deletar(Long id) {
    try {
      buscar(id);
      estadoRepository.deleteById(id);
      estadoRepository.flush();
    } catch (DataIntegrityViolationException e) {
        throw new EntidadeEmUsoException("O Estado não pode ser excluido por ter relação com alguma outra entidade");
    }
  }
}
