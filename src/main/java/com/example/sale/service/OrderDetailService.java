package com.example.sale.service;

import com.example.sale.entity.OrderDetail;
import com.example.sale.reponsitory.OrderDetailReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {
    OrderDetailReponsitory orderDetailReponsitory;
    @Autowired
    public void setOrderDetailReponsitory(OrderDetailReponsitory orderDetailReponsitory){
        this.orderDetailReponsitory = orderDetailReponsitory;
    }
    public ResponseEntity<OrderDetail> create(OrderDetail data){
        data.setTotal(data.getProductQuantity()*data.getProductId().getPrice());
        orderDetailReponsitory.save(data);
        return new ResponseEntity<>(orderDetailReponsitory.save(data), HttpStatus.OK);
    }
}
