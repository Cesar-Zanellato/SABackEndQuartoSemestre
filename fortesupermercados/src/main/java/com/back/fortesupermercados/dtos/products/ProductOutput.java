package com.back.fortesupermercados.dtos.products;

import com.back.fortesupermercados.entities.Category;
import com.back.fortesupermercados.entities.ProductStock;
import com.back.fortesupermercados.entities.Subcategory;

public record ProductOutput(
        String name,
        String valueSale,
        String promotion,
        String image,
        String amount
        ) {

}
