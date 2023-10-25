package com.nelcfood.service;

import com.nelcfood.exception.EntidadeEmUsoException;
import com.nelcfood.exception.EntidadeNaoEncontradaException;
import com.nelcfood.exception.NegocioException;
import com.nelcfood.model.entities.Cidade;
import com.nelcfood.model.entities.Estado;
import com.nelcfood.model.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
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
                () -> new EntidadeNaoEncontradaException("Cidade não foi encontrado na base de dados."));
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
