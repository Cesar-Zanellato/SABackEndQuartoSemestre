package com.back.fortesupermercados.dtos.usuarios;


public record UsuarioEntrada (
    
    String nome,
    String email,
    String telefone,
    String cpf,
    String senha
){}
