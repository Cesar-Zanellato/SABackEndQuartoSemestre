package com.back.fortesupermercados.dtos.produtos;

public record ProdutoSaida( 
    Long codigoProduto,
    String nome,
    String valorVenda,
    String promocao,
    Integer estoque,
    String imagem,
    String gramagem
){}