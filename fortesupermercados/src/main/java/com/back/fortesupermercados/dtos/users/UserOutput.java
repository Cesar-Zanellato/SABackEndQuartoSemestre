package com.back.fortesupermercados.dtos.users;

import java.util.List;

import com.back.fortesupermercados.entities.Address;
import com.back.fortesupermercados.entities.Delivery;

public record UserOutput(
    Long id,
    String name,
    String email,
    String phone,
    String cpf,
    List<Address> address,
    List<Delivery> delivery
){}
