package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.fortesupermercados.entities.Shopping;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Long>{

    
}
