package com.nelcfood.service;

import com.nelcfood.exception.EntidadeEmUsoException;
import com.nelcfood.exception.EntidadeNaoEncontradaException;
import com.nelcfood.model.entities.Estado;
import com.nelcfood.model.repository.CidadeRepository;
import com.nelcfood.model.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EstadoService {

    EstadoRepository estadoRepository;
    CidadeRepository cidadeRepository;

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscar(Long id) {
        return estadoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Estado não foi encontrado na base de dados"));
    }

    public Estado atualizar(Estado estado, Long id) {
        Estado estadoBuscado = buscar(id);
        BeanUtils.copyProperties(estadoBuscado, estado, "id");
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
        cidadeRepository.findAll().forEach(cidade -> {
            if (cidade.getEstado().getId() == estado_id) {
                throw new EntidadeEmUsoException("Esse estado possui vinculo com uma cidade");
            }
        });
    }

}
