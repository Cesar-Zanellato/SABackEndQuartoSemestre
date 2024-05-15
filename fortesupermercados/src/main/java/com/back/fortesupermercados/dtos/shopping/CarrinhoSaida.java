package com.back.fortesupermercados.dtos.shopping;

import java.util.List;

import com.back.fortesupermercados.entities.Produto;

public record CarrinhoSaida(
    
    Long id,
    List<Produto> produto,
    Float precoTotal,
    Integer quantidadeProdutos
){}
