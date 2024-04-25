package com.back.fortesupermercados.dtos.enderecos;

public record EnderecoSaida(
    
    Long id,
    String nomeRua,
    Short numeroRua,
    Short numeroCasa,
    String cep,
    String pontoReferencia,
    String complemento
){}