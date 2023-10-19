package com.nelcfood.service;

import com.nelcfood.exception.EntidadeNaoEncontrada;
import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.model.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RestauranteService {

    RestauranteRepository restauranteRepository;
    CozinhaService cozinhaService;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscar(Long id) {
        Optional<Restaurante> restaurateBuscado = restauranteRepository.findById(id);
        if (restaurateBuscado.isEmpty()) {
            throw new EntidadeNaoEncontrada("Restaurante não foi encontrado na base de dados.");
        }
        return restaurateBuscado;
    }

    public Restaurante salvar(Restaurante restaurante) {
        cozinhaService.buscar(restaurante.getCozinha().getId());
        return restauranteRepository.save(restaurante);
    }

    public Restaurante atualizar(Restaurante restaurante, Long id) {
        buscar(id);
        cozinhaService.buscar(restaurante.getCozinha().getId());
        restaurante.setId(id);
        return restauranteRepository.save(restaurante);
    }

}
