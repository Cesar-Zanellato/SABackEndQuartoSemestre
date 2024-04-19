package com.back.fortesupermercados.dtos.usuarios;

import java.util.List;

import com.back.fortesupermercados.entities.Endereco;
import com.back.fortesupermercados.entities.Pedido;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UsuarioSaida {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private List<Endereco> endereco;
    private List<Pedido> pedidos;
}
