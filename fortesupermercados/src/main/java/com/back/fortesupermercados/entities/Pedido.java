package com.back.fortesupermercados.entities;

import java.time.LocalDateTime;

import com.back.fortesupermercados.entities.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Pedido {

    @Id
    private Long id;
    @OneToOne
    private Carrinho carrinho;
    private LocalDateTime dataCriacao;
    private String horaEntrega;
    @Enumerated(EnumType.STRING)
    private Status status;
}
