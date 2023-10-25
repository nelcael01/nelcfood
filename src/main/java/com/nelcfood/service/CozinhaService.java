package com.nelcfood.service;

import com.nelcfood.exception.EntidadeEmUsoException;
import com.nelcfood.exception.EntidadeNaoEncontradaException;
import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.model.repository.CozinhaRepository;
import com.nelcfood.model.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CozinhaService {

    CozinhaRepository cozinhaRepository;
    RestauranteRepository restauranteRepository;

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Cozinha buscarPorId(Long id) {
        return cozinhaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Cozinha não foi encontrado na base de dados."));
    }

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public Cozinha atualizar(Long id, Cozinha cozinhaRecebida) {
        Cozinha cozinhaAtual = buscarPorId(id);
        BeanUtils.copyProperties(cozinhaRecebida, cozinhaAtual, "id");
        return salvar(cozinhaAtual);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        possuiVinculoComRestaurante(id);
        cozinhaRepository.deleteById(id);
    }

    private void possuiVinculoComRestaurante(Long cozinha_id) {
        restauranteRepository.findAll().forEach(restaurante -> {
            if (restaurante.getCozinha().getId() == cozinha_id) {
                throw new EntidadeEmUsoException("Essa cozinha possui vinculo com um restaurante");
            }
        });
    }
}
