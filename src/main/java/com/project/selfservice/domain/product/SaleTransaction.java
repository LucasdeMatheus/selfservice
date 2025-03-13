package com.project.selfservice.domain.product;

import com.project.selfservice.domain.stock.Stock;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "sale_transactions")
public class SaleTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate; // Data da venda

    private Integer quantitySold; // Quantidade vendida

    private BigDecimal totalSaleValue; // Valor total da venda

}
