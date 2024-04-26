package com.back.fortesupermercados.dtos.produtos;

public record ProdutoEntrada(
    
    Long codigoInterno,
    Long codigoProduto,
    String nome,
    String valorVenda,
    String valorCompra,
    String percentualLucro,
    Integer estoque,
    String imagem,
    String gramagem
){}
