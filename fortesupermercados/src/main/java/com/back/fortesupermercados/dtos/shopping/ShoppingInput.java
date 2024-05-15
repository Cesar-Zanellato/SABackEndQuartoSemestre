package com.back.fortesupermercados.dtos.shopping;

import java.util.List;

import com.back.fortesupermercados.entities.Product;

import jakarta.validation.constraints.NotNull;

public record ShoppingInput(

    @NotNull
    List<Product> product,
    @NotNull
    Float totalPrice,
    @NotNull
    Integer quantityProducts
){}
