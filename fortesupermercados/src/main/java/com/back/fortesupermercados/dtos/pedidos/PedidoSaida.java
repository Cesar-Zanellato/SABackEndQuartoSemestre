package com.back.fortesupermercados.dtos.pedidos;

import java.time.LocalDateTime;

import com.back.fortesupermercados.entities.enums.Status;
import com.back.fortesupermercados.entities.enums.TaxaEntrega;

public record PedidoSaida(

    Long id,
    LocalDateTime dataCriacao,
    String horaEntrega,
    TaxaEntrega taxaEntrega,
    Status status
){}
