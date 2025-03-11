package com.project.selfservice.domain.product;

import com.project.selfservice.domain.stock.Stock;
import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Identificador único do produto
    private String name; // Nome do produto
    private String description; // Descrição do produto
    private Double price; // Preço do produto
    private Integer available; // Disponibilidade do produto
    @Enumerated(EnumType.STRING)
    private Category category; // Categoria do produto

    @OneToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
