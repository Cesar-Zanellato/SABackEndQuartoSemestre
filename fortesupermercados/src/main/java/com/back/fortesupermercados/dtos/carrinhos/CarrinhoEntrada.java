package com.back.fortesupermercados.dtos.carrinhos;

import java.util.List;

import com.back.fortesupermercados.entities.Produto;

import jakarta.validation.constraints.NotNull;

public record CarrinhoEntrada(

    @NotNull
    List<Produto> produto,
    @NotNull
    Float precoTotal,
    @NotNull
    Integer quantidadeProdutos
){}
