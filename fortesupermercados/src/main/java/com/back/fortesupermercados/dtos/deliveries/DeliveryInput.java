package com.back.fortesupermercados.dtos.deliveries;

import java.time.LocalDateTime;
import com.back.fortesupermercados.entities.Shopping;
import com.back.fortesupermercados.entities.enums.FeeDelivery;
import com.back.fortesupermercados.entities.enums.Status;

import jakarta.validation.constraints.NotNull;

public record DeliveryInput(
    @NotNull
    Shopping shopping,
    @NotNull
    LocalDateTime dataTime,
    @NotNull
    String deliveryTime,
    @NotNull
    FeeDelivery feeDelivery,
    @NotNull
    Status status

){}
