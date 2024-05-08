package com.back.fortesupermercados.dtos.categoria;

import jakarta.validation.constraints.NotBlank;

public record CategoriaEntrada(

    @NotBlank
    String nome
){}
