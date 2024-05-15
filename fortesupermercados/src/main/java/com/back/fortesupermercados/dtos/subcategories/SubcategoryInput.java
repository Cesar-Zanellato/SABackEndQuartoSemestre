package com.back.fortesupermercados.dtos.subcategories;

import jakarta.validation.constraints.NotBlank;

public record SubcategoryInput(

    @NotBlank
    String name
){}
