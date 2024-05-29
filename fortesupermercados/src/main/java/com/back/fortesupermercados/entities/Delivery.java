package com.back.fortesupermercados.entities;

import java.time.LocalDateTime;

import com.back.fortesupermercados.entities.enums.FeeDelivery;
import com.back.fortesupermercados.entities.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Shopping shopping;
    private LocalDateTime dataTime;
    private String deliveryTime;
    @Enumerated(EnumType.STRING)
    private FeeDelivery feeDelivery;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne
    private User user;
}