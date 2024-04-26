package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.fortesupermercados.entities.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
