package com.back.fortesupermercados.entities;

import com.back.fortesupermercados.entities.enums.Categoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Produto {
    @Id 
    private Long codigoInterno;
    private Long codigoProduto;
    @Column(length = 255)
    private String nome;
    private String valorVenda; 
    private String valorCompra;
    private String percentualLucro;
    
    private String promocao;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Integer estoque;
    private String imagem;
    // @Enumerated(EnumType.STRING) 
    // private Subcategoria subcategoria; 
    private String gramagemMinima;
    private String gramagemMaxima;

}
