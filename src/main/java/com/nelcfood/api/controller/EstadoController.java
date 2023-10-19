package com.nelcfood.api.controller;

import com.nelcfood.model.entities.Estado;
import com.nelcfood.model.repository.EstadoRepository;
import com.nelcfood.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    @Autowired
    EstadoService service;

    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok().body(service.listar());
    }
}
