package com.back.fortesupermercados.dtos.produtos;

import com.back.fortesupermercados.entities.enums.Categoria;
import com.back.fortesupermercados.entities.enums.Subcategoria;

public record ProdutoSaida( 
    Long codigoProduto,
    String nome,
    String valorVenda,
    String promocao,
    Integer estoque,
    String imagem,
    String gramagem,
    Categoria categoria,
    Subcategoria subcategoria
){}