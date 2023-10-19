package com.nelcfood.api.controller;

import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.service.CozinhaService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    CozinhaService service;

    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok().body(service.listar());
    }

    @GetMapping("{id}")
    public ResponseEntity buscar(@PathVariable Long id) {
        try {
            Optional<Cozinha> cozinhaBuscada = service.buscar(id);
            return ResponseEntity.ok(cozinhaBuscada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Cozinha cozinha) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(cozinha));
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        try {
            return ResponseEntity.ok(service.atualizar(id, cozinha));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
