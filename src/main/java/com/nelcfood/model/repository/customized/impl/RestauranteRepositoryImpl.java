package com.nelcfood.model.repository.customized.impl;

import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.model.repository.customized.RestauranteRepositoryCustomized;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustomized {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> buscarRestaurantesPorNomeEIntervaloDeTaxa(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        var jpql = "from Restaurante where nome like :nome " +
                "and taxaFrete between :taxaInicial and :taxaFinal";

        return manager.createQuery(jpql, Restaurante.class)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("taxaInicial", taxaFreteInicial)
                .setParameter("taxaFinal", taxaFreteFinal)
                .getResultList();
    }
}
