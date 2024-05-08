package com.back.fortesupermercados.dtos.produtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProdutoEntrada(
    
    @NotNull @Min(value = 6) @Max(value = 6)
    Long codigoInterno,
    @NotNull @Min(value = 13) @Max(value = 13)
    Long codigoProduto,
    @NotBlank @Size(max = 255)
    String nome,
    @NotBlank
    String valorVenda,
    @NotBlank
    String valorCompra,
    @NotBlank
    String percentualLucro,
    @NotNull
    Integer estoque,
    @NotNull
    String imagem,
    @NotBlank
    String gramagem
){}
