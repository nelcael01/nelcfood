package com.nelcfood.api.controller;

import com.nelcfood.exception.EntidadeNaoEncontradaException;
import com.nelcfood.exception.NegocioException;
import com.nelcfood.model.entities.Cidade;
import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.service.CozinhaService;
import com.nelcfood.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    RestauranteService restauranteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurante> listar() {
        return restauranteService.listar();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurante buscar(@PathVariable Long id) {
        return restauranteService.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante salvar(@RequestBody Restaurante restaurante) {
        try {
            return restauranteService.salvar(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurante atualizar(@RequestBody Restaurante restaurante, @PathVariable Long id) {
        Restaurante restauranteBuscado = restauranteService.buscar(id);
        BeanUtils.copyProperties(restaurante, restauranteBuscado, "id");
        try {
            return restauranteService.salvar(restauranteBuscado);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        restauranteService.deletar(id);
    }
}

