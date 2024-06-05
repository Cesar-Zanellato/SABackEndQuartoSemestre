package com.back.fortesupermercados.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Address {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameStreet;
    private Integer numberStreet; 
    private String cep;
    private String pointReference;
    private String complement;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
