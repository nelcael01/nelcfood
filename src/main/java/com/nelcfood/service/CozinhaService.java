package com.nelcfood.service;

import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.model.repository.CozinhaRepository;
import com.nelcfood.model.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CozinhaService {

    CozinhaRepository repository;
    RestauranteRepository restauranteRepository;

    public List<Cozinha> listar() {
        return repository.findAll();
    }

    public Optional<Cozinha> buscar(Long id) {
        Optional<Cozinha> cozinhaBuscada = repository.findById(id);
        if (cozinhaBuscada.isEmpty()) {
            throw new RuntimeException("Id não encontrado na base de dados.");
        }
        return cozinhaBuscada;
    }

    public Cozinha salvar(Cozinha cozinha) {
        return repository.save(cozinha);
    }


    public Cozinha atualizar(Long id, Cozinha cozinha) {
        buscar(id);
        cozinha.setId(id);
        return salvar(cozinha);
    }

    public void deletar(Long id) {
        buscar(id);
        possuiVinculoComRestaurante(id);
        repository.deleteById(id);
    }

    private void possuiVinculoComRestaurante(Long cozinha_id) {
        List<Restaurante> allRestaurantes = restauranteRepository.findAll();
        for (Restaurante restaurante : allRestaurantes) {
            if (restaurante.getCozinha().getId() == cozinha_id) {
                throw new DataIntegrityViolationException("Essa cozinha possui vinculo com um restaurante");
            }
        }
    }

}
