package com.nelcfood.service;

import com.nelcfood.exception.EntidadeNaoEncontradaException;
import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.model.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RestauranteService {

    RestauranteRepository restauranteRepository;
    CozinhaService cozinhaService;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(Long id) {
        return restauranteRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Restaurante não foi encontrado na base de dados."));
    }

    public Restaurante salvar(Restaurante restaurante) {
        cozinhaService.buscarPorId(restaurante.getCozinha().getId());
        return restauranteRepository.save(restaurante);
    }

    public Restaurante atualizar(Restaurante restauranteRecebido, Long id) {
        Restaurante restauranteBuscado = buscar(id);
        Cozinha cozinhaBuscada = cozinhaService.buscarPorId(restauranteRecebido.getCozinha().getId());
        BeanUtils.copyProperties(restauranteRecebido, restauranteBuscado,
                "id", "formasPagamento", "endereco", "dataCadastro", "dataAtualizacao");
        restauranteBuscado.setCozinha(cozinhaBuscada);
        return restauranteRepository.save(restauranteBuscado);
    }

    public void deletar(Long id) {
        buscar(id);
        restauranteRepository.deleteById(id);
    }

}
