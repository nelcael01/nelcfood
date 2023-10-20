package com.nelcfood.api.controller;

import com.nelcfood.exception.EntidadeNaoEncontrada;
import com.nelcfood.exception.EntitidadeEmUsoException;
import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.service.CozinhaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    CozinhaService cozinhaService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(cozinhaService.listar());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        try {
            Optional<Cozinha> cozinhaBuscada = cozinhaService.buscar(id);
            return ResponseEntity.ok(cozinhaBuscada);
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cozinha cozinha) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        try {
            return ResponseEntity.ok(cozinhaService.atualizar(id, cozinha));
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            cozinhaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntitidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
