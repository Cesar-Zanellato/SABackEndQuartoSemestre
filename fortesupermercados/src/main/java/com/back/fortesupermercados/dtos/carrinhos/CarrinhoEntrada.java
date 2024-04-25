package com.back.fortesupermercados.dtos.carrinhos;

import java.util.List;

import com.back.fortesupermercados.entities.Produto;

public record CarrinhoEntrada(

    List<Produto> produto,
    Float precoTotal,
    Integer quantidadeProdutos
){}
