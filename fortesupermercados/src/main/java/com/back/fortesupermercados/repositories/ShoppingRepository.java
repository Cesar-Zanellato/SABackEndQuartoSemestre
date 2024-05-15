package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.fortesupermercados.entities.Shopping;

public interface ShoppingRepository extends JpaRepository<Shopping, Long>{

    
}
