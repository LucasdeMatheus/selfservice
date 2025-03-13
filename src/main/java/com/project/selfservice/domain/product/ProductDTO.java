package com.project.selfservice.domain.product;

public record ProductDTO(
        Long id,
        String name,
        String description,
        Double price,
        Integer available,
        Category category,
        Long stockId
) {}
