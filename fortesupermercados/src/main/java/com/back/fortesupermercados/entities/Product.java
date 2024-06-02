package com.back.fortesupermercados.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Product {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // private Long internalCode;
    // private Long codeProduct;
    private String name;
    private String valueSale; 
    private String promotion;
    private String image;
    private String amount;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Subcategory subcategory;
    @OneToOne(mappedBy = "product", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private ProductStock productStock;

}
