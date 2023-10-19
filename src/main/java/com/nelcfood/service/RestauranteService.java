package com.nelcfood.service;

import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.model.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RestauranteService {

    RestauranteRepository repository;

    public void existeRestauranteComVinculoCozinha(Long cozinha_id) {
        List<Restaurante> allRestaurantes = repository.findAll();
        for (Restaurante restaurante : allRestaurantes) {
            if (restaurante.getCozinha().getId() == cozinha_id) {
                throw new RuntimeException("Existe um restaurante com vinculo com essa cozinha");
            }
        }
    }

}
