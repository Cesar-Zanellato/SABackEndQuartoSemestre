package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.fortesupermercados.entities.User;


public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);    
}
