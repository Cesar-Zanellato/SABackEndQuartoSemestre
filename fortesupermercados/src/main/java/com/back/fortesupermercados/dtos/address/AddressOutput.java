package com.back.fortesupermercados.dtos.address;

public record AddressOutput(
    
    Long id,
    String nameStreet,
    Short numberStreet,
    Short numberHouse,
    String cep,
    String pointReference,
    String complement
){}