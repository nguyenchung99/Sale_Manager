package com.example.sale.reponsitory;

import com.example.sale.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderReponsitory extends JpaRepository<Order, Integer> {
}
