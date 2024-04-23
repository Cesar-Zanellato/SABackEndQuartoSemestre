package com.back.fortesupermercados.dtos.usuarios;

import java.util.List;

import com.back.fortesupermercados.entities.Endereco;
import com.back.fortesupermercados.entities.Pedido;

public record UsuarioSaida(
    Long id,
    String nome,
    String email,
    String telefone,
    String cpf,
    List<Endereco> endereco,
    List<Pedido> pedidos
){}
