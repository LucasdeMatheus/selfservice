package com.project.selfservice.domain.seller;

import com.project.selfservice.domain.Order;
import com.project.selfservice.domain.user.User;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "seller")
    private List<Order> sales;


    @Column(nullable = false)
    private Double commission;

    @Column(nullable = false)
    private Integer ranking;

    public Seller() {}

    public Seller(String name) {
        this.name = name;
        this.commission = 0.0;
        this.ranking = 0;
    }

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

    public List<Order> getSales() {
        return sales;
    }

    public void setSales(List<Order> sales) {
        this.sales = sales;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
