package com.back.fortesupermercados.dtos.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.back.fortesupermercados.entities.User;

public record AddressInput(
    
    @NotBlank @Size(max = 255)
    String nameStreet,
    @NotBlank
    Integer numberStreet,
    @NotBlank @Size(min = 8, max = 8)
    String cep,
    @NotBlank @Size(max = 255)
    String pointReference,
    @NotBlank @Size(max = 255)
    String complement,
    User user
){}
