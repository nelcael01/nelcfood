package com.nelcfood.service;

import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.model.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CozinhaService {

    CozinhaRepository repository;
    RestauranteService restauranteService;

    public List<Cozinha> listar() {
        return repository.findAll();
    }

    public Optional<Cozinha> buscar(Long id) {
        Optional<Cozinha> cozinhaBuscada = repository.findById(id);
        if (cozinhaBuscada.isEmpty()) {
            throw new RuntimeException("Cozinha não encontrada!");
        }
        return cozinhaBuscada;
    }

    public Cozinha salvar(Cozinha cozinha) {
        return repository.save(cozinha);
    }


    public Cozinha atualizar(Long id, Cozinha cozinha) {
        Optional<Cozinha> cozinhaBuscada = buscar(id);
        if (cozinhaBuscada.isEmpty()) {
            throw new RuntimeException("Id não encontrado na base de dados!");
        }
        cozinha.setId(id);
        return salvar(cozinha);
    }

    public void deletar(Long id) {
        buscar(id);
        restauranteService.existeRestauranteComVinculoCozinha(id);
        repository.deleteById(id);
    }
}
