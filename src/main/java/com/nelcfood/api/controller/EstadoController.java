package com.nelcfood.api.controller;

import com.nelcfood.model.entities.Estado;
import com.nelcfood.service.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/estados")
public class EstadoController {

    EstadoService estadoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Estado> listar() {
        return estadoService.listar();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Estado buscar(@PathVariable Long id) {
        return estadoService.buscar(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Estado atualizar(@RequestBody Estado estado, @PathVariable Long id) {
        return estadoService.atualizar(estado, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado salvar(@RequestBody Estado estado) {
        return estadoService.salvar(estado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        estadoService.deletar(id);
    }

}
