package com.nelcfood.service;

import com.nelcfood.model.entities.Cozinha;
import com.nelcfood.model.exception.EntidadeEmUsoException;
import com.nelcfood.model.exception.naoEncontrada.CozinhaNaoEncontradaException;
import com.nelcfood.model.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CozinhaService {

    CozinhaRepository cozinhaRepository;

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Cozinha buscarPorId(Long id) {
        return cozinhaRepository.findById(id).orElseThrow(
                () -> new CozinhaNaoEncontradaException());
    }
    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void deletar(Long id) {
        try {
            buscarPorId(id);
            cozinhaRepository.deleteById(id);
            cozinhaRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException("Essa Cozinha não pode ser excluida por que tem relação com alguma outra entidade");
        }
    }
}
