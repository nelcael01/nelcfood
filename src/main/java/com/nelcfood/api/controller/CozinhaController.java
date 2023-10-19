package com.nelcfood.api.controller;

import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    @Autowired
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
}
