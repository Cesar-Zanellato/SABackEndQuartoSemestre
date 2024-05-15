package com.back.fortesupermercados.dtos.shopping;

import java.util.List;

import com.back.fortesupermercados.entities.Product;

public record ShoppingOutput(
    
    Long id,
    List<Product> product,
    Float totalPrice,
    Integer quantityProducts
){}
