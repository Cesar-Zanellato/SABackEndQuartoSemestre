package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.fortesupermercados.entities.ProductStock;

public interface ProductStockRepository extends JpaRepository<ProductStock, Long>{
    
}
