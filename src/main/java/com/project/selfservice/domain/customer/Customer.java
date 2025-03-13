package com.project.selfservice.domain.customer;

import com.project.selfservice.domain.order.Order;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Identificador único do cliente

    private String name; // Nome do cliente


    private String phone; // Telefone do cliente

    @OneToMany(mappedBy = "customer")
    private List<Order> orderHistory;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
