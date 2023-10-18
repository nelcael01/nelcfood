package com.nelcfood.service;

import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.model.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CozinhaService {

    @Autowired
    CozinhaRepository repository;

    public List<Cozinha> listar() {
        return repository.findAll();
    }

    public Optional<Cozinha> buscar(Long id) {
        Optional<Cozinha> cozinhaBuscada = repository.findById(id);
        if (cozinhaBuscada.isEmpty()){
            throw new RuntimeException("Cozinha não encontrada!");
        }
        return cozinhaBuscada;
    }
}
