package com.nelcfood.api.controller;

import com.nelcfood.exception.EntidadeNaoEncontradaException;
import com.nelcfood.exception.EntidadeEmUsoException;
import com.nelcfood.model.entities.Estado;
import com.nelcfood.service.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/estados")
public class EstadoController {

    EstadoService estadoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(estadoService.listar());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(estadoService.buscar(id));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@RequestBody Estado estado, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(estadoService.atualizar(estado, id));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Estado estado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.salvar(estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        try {
            estadoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
