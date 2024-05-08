package com.back.fortesupermercados.dtos.subcategoria;

import jakarta.validation.constraints.NotBlank;

public record SubcategoriaEntrada(

    @NotBlank
    String nome
){}
