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
    // tao chi tiet hoa don
    public Optional<OrderDetail> create(OrderDetail data, Product product, Order order){
        data.setTotal(data.getProductQuantity()*product.getPrice());
        data.setOrder(order);
        data.setProduct(product);
        orderDetailReponsitory.save(data);
        return orderDetailReponsitory.findById(data.getId());
    }
    // chi duoc update so luong
    public OrderDetail update(OrderDetail data, OrderDetail orders){
        orders.setProductQuantity(data.getProductQuantity());
        orders.setTotal(orders.getProductQuantity()*orders.getProduct().getPrice());
        return orderDetailReponsitory.save(orders);
    }


}
