package com.example.sale.controller;


import com.example.sale.entity.Order;
import com.example.sale.reponsitory.OrderReponsitory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
    OrderReponsitory orderReponsitory;
    OrderController(OrderReponsitory orderReponsitory){
        this.orderReponsitory = orderReponsitory;
    } // inject
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR')")
    List<Order> list(){
        return orderReponsitory.findAll();
    }
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Order> create(@RequestBody Order data){
        orderReponsitory.save(data);
        return new ResponseEntity<>(orderReponsitory.save(data),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Order> update(@PathVariable("id") Integer id, @RequestBody Order data){
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
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Order> delete(@PathVariable("id") Integer id){
        boolean check = orderReponsitory.existsById(id);
        if(check == true){
            orderReponsitory.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
