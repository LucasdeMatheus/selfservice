package com.project.selfservice.controller.managementOrder;

import com.project.selfservice.domain.customer.Customer;
import com.project.selfservice.domain.customer.CustomerRepository;
import com.project.selfservice.domain.order.*;
import com.project.selfservice.domain.product.ProductRepository;
import com.project.selfservice.domain.seller.Seller;
import com.project.selfservice.domain.seller.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;
    @PostMapping("/make-order")
    public ResponseEntity<?> makeOrder(@RequestBody DataOrder dataOrder) {
        Order order = new Order();

        Optional<Customer> customer = customerRepository.findById(dataOrder.idcustomer());
        if (customer.isPresent()) {
            order.setCustomer(customer.get());
        }
        if (dataOrder.coupon() != null) {
            order.setCoupon(dataOrder.coupon());
        }

        Optional<Seller> seller = sellerRepository.findById(dataOrder.idSeller());
        if (!seller.isPresent()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vendedor não encontrado");
        }
        order.setSeller(seller.get());
        order.setPaymentMethod(dataOrder.paymentMethod());
        order.setStatus(OrderStatus.IN_PREPARATION);

        // Criar a lista de OrderItem e associar ao pedido corretamente
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItem item : dataOrder.items()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(productRepository.findById(item.getProduct().getId()).get());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getPrice());

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setOrderCode(orderService.generateOrderCode());
        orderRepository.save(order); // Isso deve persistir tanto a ordem quanto os itens, se o cascade estiver correto.

        return ResponseEntity.ok(order);
    }


}
