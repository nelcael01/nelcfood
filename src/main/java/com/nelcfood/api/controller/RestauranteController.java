package com.nelcfood.api.controller;

import com.nelcfood.exception.EntidadeNaoEncontrada;
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
            Optional<Restaurante> restauranteBuscado = restauranteService.buscar(id);
            return ResponseEntity.ok().body(restauranteBuscado);
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteService.salvar(restaurante));
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Restaurante restaurante, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(restauranteService.atualizar(restaurante, id));
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
