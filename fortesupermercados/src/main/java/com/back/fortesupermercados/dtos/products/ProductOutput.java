package com.back.fortesupermercados.dtos.products;

import com.back.fortesupermercados.entities.ProductStock;

public record ProductOutput( 
    
    // Long codeProduct,
    String name,
    String valueSale,
    String promotion,
    String image,
    String amount,
    ProductStock productStock
){}