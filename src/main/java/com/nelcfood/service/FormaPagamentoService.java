package com.nelcfood.service;

import com.nelcfood.model.entities.FormaPagamento;
import com.nelcfood.model.exception.EntidadeEmUsoException;
import com.nelcfood.model.exception.naoEncontrada.FormaPagamentoNaoEncontradaException;
import com.nelcfood.model.repository.FormaPagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FormaPagamentoService {

  FormaPagamentoRepository formaPagamentoRepository;

  public List<FormaPagamento> listar() {
    return formaPagamentoRepository.findAll();
  }

  public FormaPagamento buscar(Long id) {
    return formaPagamentoRepository.findById(id).orElseThrow(() -> new FormaPagamentoNaoEncontradaException());
  }

  public FormaPagamento salvar(FormaPagamento formaPagamento) {
    return formaPagamentoRepository.save(formaPagamento);
  }

  public void excluir(Long id) {
    try {
      buscar(id);
      formaPagamentoRepository.deleteById(id);
      formaPagamentoRepository.flush();
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException
              ("Essa Forma de Pagamento não pode ser excluida por que tem relação com alguma outra entidade");
    }
  }
}
