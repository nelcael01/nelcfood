package com.nelcfood.api.controller;

import com.nelcfood.service.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/estados")
public class EstadoController {

    EstadoService estadoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(estadoService.listar());
    }
}
