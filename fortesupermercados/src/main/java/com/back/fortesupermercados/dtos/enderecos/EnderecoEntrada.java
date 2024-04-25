package com.back.fortesupermercados.dtos.enderecos;

public record EnderecoEntrada(
    
    String nomeRua,
    Short numeroRua,
    Short numeroCasa,
    String cep,
    String pontoReferencia,
    String complemento
){}
