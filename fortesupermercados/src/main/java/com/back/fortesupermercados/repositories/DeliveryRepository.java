package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.fortesupermercados.entities.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>{
    
}
