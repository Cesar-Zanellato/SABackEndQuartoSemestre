package com.back.fortesupermercados.dtos.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioEntrada (
    
    @NotBlank @Size(max = 255)
    String nome,
    @NotNull @Email @Size(max = 255)
    String email,
    @NotNull @Size(min = 10, max = 11)
    String telefone,
    @NotNull @Size(min = 11, max = 11)
    String cpf,
    @NotNull @Size(min = 8, max = 32)
    String senha
){}
