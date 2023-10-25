package com.nelcfood.api.controller;

import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.service.CozinhaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    CozinhaService cozinhaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cozinha> listar() {
        return cozinhaService.listar();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cozinha buscarPorId(@PathVariable Long id) {
        return cozinhaService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha salvar(@RequestBody Cozinha cozinha) {
        return cozinhaService.salvar(cozinha);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cozinha atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        return cozinhaService.atualizar(id, cozinha);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        cozinhaService.deletar(id);
    }
}
