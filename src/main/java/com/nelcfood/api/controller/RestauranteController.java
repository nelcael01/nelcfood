package com.nelcfood.api.controller;

import com.nelcfood.exception.EntidadeNaoEncontradaException;
import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(restauranteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        try {
            Restaurante restauranteBuscado = restauranteService.buscar(id);
            return ResponseEntity.ok().body(restauranteBuscado);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteSalvo = restauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Restaurante restaurante, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(restauranteService.atualizar(restaurante, id));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            restauranteService.deletar(id);
            return ResponseEntity.ok().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

