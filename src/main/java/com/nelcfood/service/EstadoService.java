package com.nelcfood.service;

import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.model.entities.Estado;
import com.nelcfood.model.repository.CozinhaRepository;
import com.nelcfood.model.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
    @Autowired
    EstadoRepository repository;

    public List<Estado> listar() {
        return repository.findAll();
    }

}
