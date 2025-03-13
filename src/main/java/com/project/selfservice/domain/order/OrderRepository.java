package com.project.selfservice.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("SELECT COALESCE(MAX(o.orderCode), 0) FROM Order o")
    String getLastOrderCode();
}
