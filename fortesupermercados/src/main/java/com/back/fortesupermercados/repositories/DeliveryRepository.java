package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.fortesupermercados.entities.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long>{
    
}
