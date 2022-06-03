package com.example.sale.controller;

import com.example.sale.entity.OrderDetail;
import com.example.sale.reponsitory.OrderDetailReponsitory;
import com.example.sale.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order-detail")
public class OrderDetailController {
    OrderDetailReponsitory orderDetailReponsitory;

    @Autowired
    OrderDetailService orderDetailService;

    OrderDetailController(OrderDetailReponsitory orderDetailReponsitory){
        this.orderDetailReponsitory = orderDetailReponsitory;
    } // injec
    // get data
    @GetMapping("/get")
    List<OrderDetail> list(){
        return orderDetailReponsitory.findAll();
    }
    // post data
    @PostMapping("/post")
    ResponseEntity<OrderDetail> create(@RequestBody OrderDetail data){
        return orderDetailService.create(data);
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
