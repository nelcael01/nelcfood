package com.nelcfood.model.repository;

import com.nelcfood.model.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>  {

}
