package com.back.fortesupermercados.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
    
    @OneToOne(mappedBy = "address", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private User user;
}
