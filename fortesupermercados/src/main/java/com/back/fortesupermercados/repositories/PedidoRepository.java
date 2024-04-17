package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.fortesupermercados.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
}
