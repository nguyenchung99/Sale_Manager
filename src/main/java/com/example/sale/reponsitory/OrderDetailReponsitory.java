package com.example.sale.reponsitory;

import com.example.sale.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailReponsitory extends JpaRepository<OrderDetail, Integer> {
}
