package com.nelcfood.model.repository.customized;

import com.nelcfood.model.entities.Restaurante;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepositoryCustomized {
    List<Restaurante> buscarRestaurantesPorNomeEIntervaloDeTaxa(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
}
