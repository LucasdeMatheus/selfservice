package com.project.selfservice.domain.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    public String generateOrderCode() {
        String lastOrderCode = orderRepository.getLastOrderCode();

        // Se lastOrderCode for null, assumimos que é o primeiro pedido
        int nextOrderNumber = (lastOrderCode != null) ? Integer.parseInt(lastOrderCode) + 1 : 1;

        // Formata para 3 dígitos (exemplo: 001, 002, ..., 999)
        return String.format("%03d", nextOrderNumber);
    }
}
