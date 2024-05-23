package com.back.fortesupermercados.dtos.subcategories;

import com.back.fortesupermercados.entities.Category;

public record SubcategoryOutput(

    Long id,
    String name,
    Category category
){}
