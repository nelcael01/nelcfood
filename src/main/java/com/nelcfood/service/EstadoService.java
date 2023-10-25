package com.nelcfood.service;

import com.nelcfood.exception.EntidadeNaoEncontradaException;
import com.nelcfood.exception.EntidadeEmUsoException;
import com.nelcfood.model.entities.Cidade;
import com.nelcfood.model.entities.Estado;
import com.nelcfood.model.repository.CidadeRepository;
import com.nelcfood.model.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EstadoService {

    EstadoRepository estadoRepository;
    CidadeRepository cidadeRepository;

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscar(Long id) {
        Optional<Estado> estadoEncontrado = estadoRepository.findById(id);
        if (estadoEncontrado.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Estado não foi encontrado na base de dados.");
        }
        return estadoEncontrado.get();
    }

    public Estado atualizar(Estado estado, Long id) {
        buscar(id);
        estado.setId(id);
        return estadoRepository.save(estado);
    }


    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }
    public void deletar(Long id) {
        buscar(id);
        possuiVinculoComCidade(id);
        estadoRepository.deleteById(id);
    }

    private void possuiVinculoComCidade(Long estado_id) {
        List<Cidade> cidades = cidadeRepository.findAll();
        for (Cidade cidade : cidades) {
            if (cidade.getEstado().getId() == estado_id) {
                throw new EntidadeEmUsoException("Esse estado possui vinculo com uma cidade");
            }
        }
    }

}
