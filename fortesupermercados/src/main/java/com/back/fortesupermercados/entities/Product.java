package com.back.fortesupermercados.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id 
    private Long id;
    private Long internalCode;
    private Long codeProduct;
    private String name;
    private String valueSale; 
    private String valuePurchase;
    private String percentageProfit;
    private String promotion;
    private String image;
    private String stock;
    private String amount;
    @OneToOne
    private Category category;
    @OneToOne
    private Subcategory subcategory;

}
