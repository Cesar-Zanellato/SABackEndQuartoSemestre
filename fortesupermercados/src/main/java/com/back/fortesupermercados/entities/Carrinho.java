package com.back.fortesupermercados.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Carrinho {

    @Id
    private Long id;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos;
    private Float precoTotal;
    private Integer quantidadeProdutos;
    
}
