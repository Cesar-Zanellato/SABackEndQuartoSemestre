package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.fortesupermercados.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
