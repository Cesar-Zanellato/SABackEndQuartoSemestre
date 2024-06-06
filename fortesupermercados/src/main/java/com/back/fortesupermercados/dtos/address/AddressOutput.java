package com.back.fortesupermercados.dtos.address;

import com.back.fortesupermercados.entities.User;

public record AddressOutput(
    
    Long id,
    String nameStreet,
    Integer numberStreet,
    String cep,
    String pointReference,
    String complement,
    User user
){}