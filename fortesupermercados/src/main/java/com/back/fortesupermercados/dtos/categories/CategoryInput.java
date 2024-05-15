package com.back.fortesupermercados.dtos.categories;

import jakarta.validation.constraints.NotBlank;

public record CategoryInput(

    @NotBlank
    String name
){}
