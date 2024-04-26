package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.fortesupermercados.entities.Subcategoria;

public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long>{
    
}
