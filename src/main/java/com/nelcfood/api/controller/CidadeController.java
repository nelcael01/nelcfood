package com.nelcfood.api.controller;

import com.nelcfood.exception.naoEncontrada.EstadoNaoEncontradoException;
import com.nelcfood.exception.NegocioException;
import com.nelcfood.model.entities.Cidade;
import com.nelcfood.service.CidadeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cidades")
public class CidadeController {

    CidadeService cidadeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cidade> listar() {
        return cidadeService.listar();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cidade buscar(@PathVariable Long id) {
        return cidadeService.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade salvar(@RequestBody Cidade cidade) {
        try {
            return cidadeService.salvar(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cidade atualizar(@RequestBody Cidade cidade, @PathVariable Long id) {
        Cidade cidadeBuscada = cidadeService.buscar(id);
        BeanUtils.copyProperties(cidade, cidadeBuscada, "id");
        try {
            return cidadeService.salvar(cidadeBuscada);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        cidadeService.deletar(id);
    }
}
