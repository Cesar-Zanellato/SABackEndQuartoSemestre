package com.back.fortesupermercados.dtos.products;

public record ProductOutput( 
    Long codeProduct,
    String name,
    String valueSale,
    String promotion,
    String image,
    Integer stock,
    String amount
){}