package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.fortesupermercados.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}
