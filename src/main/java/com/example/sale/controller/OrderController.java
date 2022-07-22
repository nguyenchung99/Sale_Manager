package com.example.sale.controller;


import com.example.sale.entity.Order;
import com.example.sale.reponsitory.OrderReponsitory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
    OrderReponsitory orderReponsitory;
    OrderController(OrderReponsitory orderReponsitory){
        this.orderReponsitory = orderReponsitory;
    } // inject
    @GetMapping("/get")
    List<Order> list(){
        return orderReponsitory.findAll();
    }
    @PostMapping("/post")
    ResponseEntity<Order> create(@RequestBody Order data){
        orderReponsitory.save(data);
        return new ResponseEntity<>(orderReponsitory.save(data),HttpStatus.OK);
    }
    @PutMapping("/put/{id}")
    ResponseEntity<Order> update(@PathVariable Integer id, @RequestBody Order data){
        Order orders = orderReponsitory.findById(id).orElseGet(() -> null);
        if(orders !=null){
            orders.setName(data.getName());
            orders.setCustomerAdress(data.getCustomerAdress());
            orders.setCustomerName(data.getCustomerName());
            orderReponsitory.save(orders);
            return new ResponseEntity<>(orderReponsitory.save(orders),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete")
    ResponseEntity<Order> delete(@RequestBody Integer id){
        boolean check = orderReponsitory.existsById(id);
        if(check == true){
            orderReponsitory.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
