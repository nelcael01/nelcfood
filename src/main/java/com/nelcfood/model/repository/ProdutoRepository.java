package com.nelcfood.model.repository;

import com.nelcfood.model.entities.Produto;
import com.nelcfood.model.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findTodosByRestaurante(Restaurante restaurante);

    @Query("from produto where ativo = true and restaurante = :restaurante")
    List<Produto> findAtivosByRestaurante(Restaurante restaurante);
}
