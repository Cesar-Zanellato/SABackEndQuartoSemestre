package com.back.fortesupermercados.dtos.deliveries;

import java.time.LocalDateTime;

import com.back.fortesupermercados.entities.User;
import com.back.fortesupermercados.entities.enums.FeeDelivery;
import com.back.fortesupermercados.entities.enums.Status;

public record DeliveryOutput(

    Long id,
    LocalDateTime dataTime,
    String deliveryTime,
    FeeDelivery feeDelivery,
    Status status,
    User user
){}
