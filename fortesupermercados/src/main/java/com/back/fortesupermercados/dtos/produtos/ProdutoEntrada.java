package com.back.fortesupermercados.dtos.produtos;

import com.back.fortesupermercados.entities.enums.Categoria;
import com.back.fortesupermercados.entities.enums.Subcategoria;

public record ProdutoEntrada(
    
    Long codigoInterno,
    Long codigoProduto,
    String nome,
    String valorVenda,
    String valorCompra,
    String percentualLucro,
    Integer estoque,
    String imagem,
    Categoria categoria,
    Subcategoria subcategoria,
    String gramagem
){}
