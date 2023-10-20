package com.nelcfood.api.controller;

import com.nelcfood.exception.EntidadeNaoEncontrada;
import com.nelcfood.model.entities.Cidade;
import com.nelcfood.service.CidadeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/cidades")
public class CidadeController {

    CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(cidadeService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        try {
            Optional<Cidade> cidadeBuscada = cidadeService.buscar(id);
            return ResponseEntity.ok(cidadeBuscada);
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Cidade cidade, @PathVariable Long id) {
        try {
            Cidade cidadeAtualizada = cidadeService.atualizar(cidade, id);
            return ResponseEntity.ok(cidadeAtualizada);
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cidade cidade) {
        try {
            Cidade cidadeSalva = cidadeService.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            cidadeService.deletar(id);
            return ResponseEntity.ok().build();
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.notFound().build();
        }
    }
}
