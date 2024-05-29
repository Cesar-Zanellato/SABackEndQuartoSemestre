package com.back.fortesupermercados.dtos.products;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Size;

public record ProductInput(
    
    @NotNull @Min(value = 6) @Max(value = 6)
    // Long internalCode,
    // @NotNull @Min(value = 13) @Max(value = 13)
    // Long codeProduct,
    // @NotBlank @Size(max = 255)
    String name,
    @NotBlank
    String valueSale,
    @NotBlank
    String valuePurchase,
    // @NotNull
    String image,
    @NotNull
    String stock,
    @NotBlank
    String amount
){}
