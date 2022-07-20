package com.example.sale.controller;

import com.example.sale.entity.Order;
import com.example.sale.entity.OrderDetail;
import com.example.sale.entity.Product;
import com.example.sale.reponsitory.OrderDetailReponsitory;
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
    @Autowired
    OrderDetailService orderDetailService;

    OrderDetailController(OrderDetailReponsitory orderDetailReponsitory, ProductReponsitory productReponsitory){
        this.orderDetailReponsitory = orderDetailReponsitory;
        this.productReponsitory = productReponsitory;
    } // inject

    @GetMapping("/get")
    List<OrderDetail> list(){
        return orderDetailReponsitory.findAll();
    }
    @PostMapping("/post/{product_id}")
    ResponseEntity<OrderDetail> create(@RequestBody OrderDetail data, @PathVariable Integer product_id){
        Product product = productReponsitory.getById(product_id);
        System.out.println(product);
        if(product != null){
            return orderDetailService.create(data, product);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    @DeleteMapping("/delete")
    ResponseEntity<OrderDetail> delete(@RequestBody Integer id){
        boolean check = orderDetailReponsitory.existsById(id);
        if(check == true){
            orderDetailReponsitory.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
