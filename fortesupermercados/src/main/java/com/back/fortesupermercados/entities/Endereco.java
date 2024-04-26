package com.back.fortesupermercados.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Endereco {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 255)
    private String nomeRua;
    private Short numeroRua; 
    private Short numeroCasa;
    @Column(length = 8)
    private String cep;
    @Column(length = 255)
    private String pontoReferencia;
    @Column(length = 255)
    private String complemento;
    
}
