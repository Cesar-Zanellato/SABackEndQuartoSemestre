package com.back.fortesupermercados.dtos.pedidos;

import java.time.LocalDateTime;
import com.back.fortesupermercados.entities.Carrinho;
import com.back.fortesupermercados.entities.enums.Status;
import com.back.fortesupermercados.entities.enums.TaxaEntrega;

import jakarta.validation.constraints.NotNull;

public record PedidoEntrada(
    @NotNull
    Carrinho carrinho,
    @NotNull
    LocalDateTime dataCriacao,
    @NotNull
    String horaEntrega,
    @NotNull
    TaxaEntrega taxaEntrega,
    @NotNull
    Status status

){}
