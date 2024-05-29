package com.back.fortesupermercados.dtos.users;

import com.back.fortesupermercados.entities.Address;

public record UserOutput(
    Long id,
    String name,
    String email,
    String phone,
    String cpf,
    Address address
){}
