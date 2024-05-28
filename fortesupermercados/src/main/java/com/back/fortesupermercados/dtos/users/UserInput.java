package com.back.fortesupermercados.dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserInput (
    
    @NotBlank @Size(max = 255)
    String name,
    @NotNull @Email @Size(max = 255)
    String email,
    @NotNull @Size(min = 10, max = 11)
    String phone,
    @NotNull @Size(min = 11, max = 11)
    String cpf,
    @NotNull @Size(min = 8, max = 32)
    String password
){}
