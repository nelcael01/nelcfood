package com.nelcfood.service;

import com.nelcfood.exception.EntidadeNaoEncontrada;
import com.nelcfood.model.entities.Estado;
import com.nelcfood.model.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
    @Autowired
    EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Optional<Estado> buscar(Long id) {
        Optional<Estado> estadoEncontrado = estadoRepository.findById(id);
        if (estadoEncontrado.isEmpty()) {
            throw new EntidadeNaoEncontrada("Estado não foi encontrado na base de dados.");
        }
        return estadoEncontrado;
    }

}
