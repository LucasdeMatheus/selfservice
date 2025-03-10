package com.project.selfservice.domain.customer;

import com.project.selfservice.domain.Order;
import com.project.selfservice.domain.user.User;
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


    @OneToOne
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
