package com.back.fortesupermercados.dtos.address;

public record AddressOutput(
    
    Long id,
    String nameStreet,
    Integer numberStreet,
    String cep,
    String pointReference,
    String complement
){}