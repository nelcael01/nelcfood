package com.nelcfood.service;

import com.nelcfood.exception.EntidadeNaoEncontradaException;
import com.nelcfood.model.entities.Cidade;
import com.nelcfood.model.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        estadoService.buscar(cidade.getEstado().getId());
        return cidadeRepository.save(cidade);
    }

    public Cidade atualizar(Cidade cidadeRecebida, Long id) {
        Cidade cidadeBuscada = buscar(id);
        estadoService.buscar(cidadeRecebida.getEstado().getId());
        BeanUtils.copyProperties(cidadeBuscada, cidadeRecebida, "id");
        return cidadeRepository.save(cidadeRecebida);
    }

    public void deletar(Long id) {
        buscar(id);
        cidadeRepository.deleteById(id);
    }

}
