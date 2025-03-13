package com.project.selfservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code; // Código do cupom
    private Double discount; // Valor do desconto
    private DiscountType discountType; // Tipo de desconto
    private LocalDate expirationDate; // Data de validade do cupom
    private String restrictions; // Restrições do cupom

}
