package com.back.fortesupermercados.repositories;

import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Category, Long>{

    
}
