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
    private Long id;
    @Column(length = 255)
    private String nome;
    private String valorVenda; 
    private String promocao;
    private Integer estoque; 
    private String imagem;
    private String valorCompra;
    private String valorLucro;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    // @Enumerated(EnumType.STRING) 
    // private Subcategoria subcategoria; 
    // @Enumerated(EnumType.STRING)
    // private Gramagem gramagem;

}
