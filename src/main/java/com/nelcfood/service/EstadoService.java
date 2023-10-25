package com.nelcfood.service;

import com.nelcfood.exception.EntidadeEmUsoException;
import com.nelcfood.exception.naoEncontrada.EstadoNaoEncontradoException;
import com.nelcfood.model.entities.Estado;
import com.nelcfood.model.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EstadoService {

    EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscar(Long id) {
        return estadoRepository.findById(id).
                orElseThrow(() -> new EstadoNaoEncontradoException());
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void deletar(Long id) {
        try {
            buscar(id);
            estadoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException("Esse Estado não pode ser excluido por que tem relação com alguma outra entidade");
        }
    }
}
