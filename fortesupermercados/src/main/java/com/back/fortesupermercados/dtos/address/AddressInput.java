package com.back.fortesupermercados.dtos.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressInput(
    
    @NotBlank @Size(max = 255)
    String nameStreet,
    @NotBlank
    Short numberStreet,
    @NotBlank @Size(min = 8, max = 8)
    String cep,
    @NotBlank @Size(max = 255)
    String pointReference,
    @NotBlank @Size(max = 255)
    String complement
){}
