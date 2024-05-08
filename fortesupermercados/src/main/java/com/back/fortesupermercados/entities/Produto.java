package com.back.fortesupermercados.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Produto {
    @Id 
    private Long id;
    private Long codigoInterno;
    private Long codigoProduto;
    private String nome;
    private String valorVenda; 
    private String valorCompra;
    private String percentualLucro;
    private String promocao;
    private Integer estoque;
    private String imagem;
    private String gramagem;
    @OneToOne
    private Categoria categoria;
    @OneToOne
    private Subcategoria subcategoria;

}
