package com.back.fortesupermercados.dtos.enderecos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoEntrada(
    
    @NotBlank @Size(max = 255)
    String nomeRua,
    @NotBlank
    Short numeroRua,
    @NotBlank
    Short numeroCasa,
    @NotBlank @Size(min = 8, max = 8)
    String cep,
    @NotBlank @Size(max = 255)
    String pontoReferencia,
    @NotBlank @Size(max = 255)
    String complemento
){}
