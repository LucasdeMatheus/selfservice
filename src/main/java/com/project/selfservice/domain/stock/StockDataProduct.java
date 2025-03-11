package com.project.selfservice.domain.stock;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record StockDataProduct(

        @NotNull
        Integer quantityInStock, // quantidade
        @NotNull
        Integer minimumQuantity, // quantidade mínima
        @NotNull
        Integer maximumQuantity, // quantidade máxima
        @NotBlank
        BigDecimal costPrice, // custo
        @NotNull
        ProductStatus status // "ATIVO", "INATIVO", "CRÍTICO", "ESGOTADO"
) {}
//{"idproduct": 2,
// "quantityInStock": 10,
// "minimumQuantity": 5,
// "maximumQuantity": 10,
// "costPrice": 5.0,
// "sellingPrice": 5.0,
// "status": "ATIVO"
// }