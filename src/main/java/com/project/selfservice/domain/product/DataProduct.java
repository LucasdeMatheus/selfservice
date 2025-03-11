package com.project.selfservice.domain.product;

import com.project.selfservice.domain.user.User;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataProduct(
        @NotNull
        User user,
        @NotBlank
        String name,
        String description,
        @NotNull(message = "O preço não pode ser nulo.")
        @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser um número positivo.")
        Double price,
        @Min(value = 0, message = "O valor mínimo permitido para available é 0.")
        Integer available,
        @NotNull
        Category category
) {
}
//private long id; // Identificador único do produto
//private String name; // Nome do produto
//private String description; // Descrição do produto
//private Double price; // Preço do produto
//private Boolean available; // Disponibilidade do produto
//@ManyToOne
//@JoinColumn(name = "category_id", nullable = false)
//private Category category;