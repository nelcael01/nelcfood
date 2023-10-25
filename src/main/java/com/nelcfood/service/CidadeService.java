package com.nelcfood.service;

import com.nelcfood.model.exception.naoEncontrada.CidadeNaoEncontradaException;
import com.nelcfood.model.exception.EntidadeEmUsoException;
import com.nelcfood.model.entities.Cidade;
import com.nelcfood.model.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CidadeService {

    CidadeRepository cidadeRepository;
    EstadoService estadoService;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscar(Long id) {
        return cidadeRepository.findById(id).orElseThrow(
                () -> new CidadeNaoEncontradaException());
    }

    public Cidade salvar(Cidade cidade) {
        cidade.setEstado(estadoService.buscar(cidade.getEstado().getId()));
        return cidadeRepository.save(cidade);
    }

    public void deletar(Long id) {
        try {
            buscar(id);
            cidadeRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException("Essa cidade não pode ser excluida por que tem relação com alguma outra entidade");
        }
    }
}
