package com.example.sale.reponsitory;

import com.example.sale.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReponsitory extends JpaRepository<Order, Integer> {
}
