package com.back.fortesupermercados.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Usuario {
    @Id 
    private Long id;
    @Column(length = 255)
    private String nome;
    @Column(length = 255)
    private String email;
    @Column(length = 9)
    private String telefone;
    @Column(length = 11)
    private String cpf;
    @Column(length = 26)
    private String senha;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Endereco> endereco;
    @OneToOne
    private Carrinho carrinho;
    @OneToMany
    private List<Pedido> pedidos;
}
