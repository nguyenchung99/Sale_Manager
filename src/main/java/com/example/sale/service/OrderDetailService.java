package com.example.sale.service;

import com.example.sale.entity.Order;
import com.example.sale.entity.OrderDetail;
import com.example.sale.entity.Product;
import com.example.sale.reponsitory.OrderDetailReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailService {
    OrderDetailReponsitory orderDetailReponsitory;
    @Autowired
    public void setOrderDetailReponsitory(OrderDetailReponsitory orderDetailReponsitory){
        this.orderDetailReponsitory = orderDetailReponsitory;
    }
    public Optional<OrderDetail> create(OrderDetail data, Product product, Order order){
        data.setTotal(data.getProductQuantity()*product.getPrice());
        data.setOrderId(order);
        data.setProduct(product);
        orderDetailReponsitory.save(data);
        return orderDetailReponsitory.findById(data.getId());
    }


}
