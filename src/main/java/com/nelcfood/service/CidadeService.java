package com.nelcfood.service;

import com.nelcfood.exception.EntidadeNaoEncontrada;
import com.nelcfood.model.entities.Cidade;
import com.nelcfood.model.repository.CidadeRepository;
import lombok.AllArgsConstructor;
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

    public Optional<Cidade> buscar(Long id) {
        Optional<Cidade> cidadeBuscada = cidadeRepository.findById(id);
        if (cidadeBuscada.isEmpty()) {
            throw new EntidadeNaoEncontrada("Cidade não foi encontrado na base de dados.");
        }
        return cidadeBuscada;
    }

    public Cidade salvar(Cidade cidade) {
        estadoService.buscar(cidade.getEstado().getId());
        return cidadeRepository.save(cidade);
    }

    public Cidade atualizar(Cidade cidade, Long id) {
        buscar(id);
        estadoService.buscar(cidade.getEstado().getId());
        cidade.setId(id);
        return cidadeRepository.save(cidade);
    }

    public void deletar(Long id) {
        buscar(id);
        cidadeRepository.deleteById(id);
    }
}
