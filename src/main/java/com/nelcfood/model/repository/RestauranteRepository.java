package com.nelcfood.model.repository;

import com.nelcfood.model.entities.Restaurante;
import com.nelcfood.model.repository.customized.RestauranteRepositoryCustomized;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryCustomized {

}
