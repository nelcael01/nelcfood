package com.nelcfood.service;

import com.nelcfood.exception.EntidadeNaoEncontradaException;
import com.nelcfood.exception.EntitidadeEmUsoException;
import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.model.repository.CozinhaRepository;
import com.nelcfood.model.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CozinhaService {

    CozinhaRepository cozinhaRepository;
    RestauranteRepository restauranteRepository;

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Optional<Cozinha> buscar(Long id) {
        Optional<Cozinha> cozinhaBuscada = cozinhaRepository.findById(id);
        if (cozinhaBuscada.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Cozinha não foi encontrado na base de dados.");
        }
        return cozinhaBuscada;
    }

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }


    public Cozinha atualizar(Long id, Cozinha cozinha) {
        buscar(id);
        cozinha.setId(id);
        return salvar(cozinha);
    }

    public void deletar(Long id) {
        buscar(id);
        possuiVinculoComRestaurante(id);
        cozinhaRepository.deleteById(id);
    }

    private void possuiVinculoComRestaurante(Long cozinha_id) {
        List<Restaurante> allRestaurantes = restauranteRepository.findAll();
        for (Restaurante restaurante : allRestaurantes) {
            if (restaurante.getCozinha().getId() == cozinha_id) {
                throw new EntitidadeEmUsoException("Essa cozinha possui vinculo com um restaurante");
            }
        }
    }

}
