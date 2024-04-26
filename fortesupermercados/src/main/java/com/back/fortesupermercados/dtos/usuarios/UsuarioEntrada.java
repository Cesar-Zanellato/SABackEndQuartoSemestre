package com.back.fortesupermercados.dtos.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioEntrada (
    
    @NotBlank
    String nome,
    @NotNull @Email
    String email,
    @NotNull @Min(value = 10) @Max(value = 11)
    String telefone,
    @NotNull @Min(value = 11) @Max(value = 11)
    String cpf,
    @NotNull @Size(min = 8)
    String senha
){}
