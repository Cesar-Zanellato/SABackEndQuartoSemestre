package com.back.fortesupermercados.dtos.subcategories;

import com.back.fortesupermercados.entities.Category;

import jakarta.validation.constraints.NotBlank;

public record SubcategoryInput(
        @NotBlank
        String name,
        Category category) {

}
