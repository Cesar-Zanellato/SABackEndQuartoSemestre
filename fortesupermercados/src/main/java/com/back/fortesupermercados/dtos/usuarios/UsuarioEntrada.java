package com.back.fortesupermercados.dtos.usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UsuarioEntrada {
    
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String senha;
}
