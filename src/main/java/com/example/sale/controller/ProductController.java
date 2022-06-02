package com.example.sale.controller;

import com.example.sale.entity.Product;
import com.example.sale.reponsitory.ProductReponsitory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
    ProductReponsitory productReponsitory;
    ProductController (ProductReponsitory productReponsitory){
        this.productReponsitory = productReponsitory;
    }
    @GetMapping("/get")
    List<Product> list(){
        return productReponsitory.findAll();
    }
    @PostMapping(value = "/post")
    Product create(@RequestBody Product data){
        return productReponsitory.save(data);
    }
    @PutMapping("/put")
    ResponseEntity<Product> update(@PathVariable(value = "id") Integer id, @RequestBody Product data){
        Product temp = productReponsitory.getById(id);
        if(temp != null){
            data.setId(temp.getId());
            productReponsitory.save(data);
        }else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productReponsitory.save(data), HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    ResponseEntity delete(@PathVariable(value = "id") Integer id){
        boolean checkid = productReponsitory.existsById(id);
        if(checkid){
            productReponsitory.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
