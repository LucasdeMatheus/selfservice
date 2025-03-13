package com.project.selfservice.domain.order;

import com.project.selfservice.domain.Coupon;
import com.project.selfservice.domain.PaymentMethod;
import com.project.selfservice.domain.customer.Customer;
import com.project.selfservice.domain.seller.Seller;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DataOrder(
    Long idcustomer,
    @NotNull
    List<OrderItem> items,
    @NotNull
    PaymentMethod paymentMethod,
    @NotNull
    Long idSeller,
    Coupon coupon
) {
}

