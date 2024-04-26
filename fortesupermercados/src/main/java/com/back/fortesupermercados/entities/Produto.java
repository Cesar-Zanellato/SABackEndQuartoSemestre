package com.back.fortesupermercados.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private Integer estoque;
    private String imagem;
    private String gramagem;

}
