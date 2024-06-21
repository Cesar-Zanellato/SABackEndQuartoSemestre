package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.fortesupermercados.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

    
}
