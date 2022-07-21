package com.example.sale.controller;

import com.example.sale.entity.Order;
import com.example.sale.entity.OrderDetail;
import com.example.sale.entity.Product;
import com.example.sale.reponsitory.OrderDetailReponsitory;
import com.example.sale.reponsitory.OrderReponsitory;
import com.example.sale.reponsitory.ProductReponsitory;
import com.example.sale.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/order-detail")
public class OrderDetailController {
    OrderDetailReponsitory orderDetailReponsitory;
    ProductReponsitory productReponsitory;
    OrderReponsitory orderReponsitory;
    @Autowired
    OrderDetailService orderDetailService;

    OrderDetailController(OrderDetailReponsitory orderDetailReponsitory, ProductReponsitory productReponsitory, OrderReponsitory orderReponsitory){
        this.orderDetailReponsitory = orderDetailReponsitory;
        this.productReponsitory = productReponsitory;
        this.orderReponsitory = orderReponsitory;
    } // inject

    @GetMapping("/get")
    List<OrderDetail> list(){
        return orderDetailReponsitory.findAll();
    }
    @PostMapping("/post")
    OrderDetail create(@RequestBody OrderDetail data){
       Optional <Product> product = productReponsitory.findById(data.getProduct().getId());
       Optional <Order> order = orderReponsitory.findById(data.getOrderId().getId());
        if(product.isPresent() && order.isPresent()){
            Optional <OrderDetail> orderDetail = orderDetailService.create(data, product.get(), order.get());
            return orderDetail.get();
        }
        ResponseEntity.status(500).body("ERRO");
        return null;
    }
    @PutMapping("/put")
    ResponseEntity<OrderDetail> update(@RequestBody Integer id, OrderDetail data){
        OrderDetail orders = orderDetailReponsitory.getById(id);
        if(orders != null){
            data.setId(orders.getId());
            return new ResponseEntity<>(orderDetailReponsitory.save(orders),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
