package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.fortesupermercados.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

    
}
