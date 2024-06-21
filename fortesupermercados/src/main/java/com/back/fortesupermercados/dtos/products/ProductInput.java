package com.back.fortesupermercados.dtos.products;

import com.back.fortesupermercados.entities.ProductStock;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductInput(
    
    @NotBlank @Size(max = 255)
    String name,
    @NotBlank
    String valueSale,
    @NotNull
    String image,
    @NotBlank
    String amount,
    ProductStock productStock
){}
