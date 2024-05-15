package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.fortesupermercados.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
