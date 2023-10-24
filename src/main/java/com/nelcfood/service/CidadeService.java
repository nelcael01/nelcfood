package com.nelcfood.service;

import com.nelcfood.exception.EntidadeNaoEncontradaException;
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

    public Cidade buscar(Long id) {
        Optional<Cidade> cidadeBuscada = cidadeRepository.findById(id);
        if (cidadeBuscada.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Cidade não foi encontrado na base de dados.");
        }
        return cidadeBuscada.get();
    }

    public Cidade salvar(Cidade cidade) {
        estadoService.buscar(cidade.getEstado().getId());
        return cidadeRepository.save(cidade);
    }

    public Cidade atualizar(Cidade cidade, Long id) {
        buscar(id);
        estadoService.buscar(cidade.getEstado().getId());
        cidade.setId(id);
        System.out.println("Cidade que chega " + cidade + "\n");
        System.out.println("Cidade que retonar " + cidadeRepository.save(cidade) + "\n");
        return cidadeRepository.save(cidade);
    }

    public void deletar(Long id) {
        buscar(id);
        cidadeRepository.deleteById(id);
    }

}
