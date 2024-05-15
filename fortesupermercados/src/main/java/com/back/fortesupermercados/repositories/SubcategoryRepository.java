package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.fortesupermercados.entities.Subcategory;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long>{
    
}
