package com.example.sale.controller;

import com.example.sale.entity.Product;
import com.example.sale.models.ProductSearch;
import com.example.sale.reponsitory.ProductReponsitory;
import com.example.sale.response.ResponsePage;
import com.example.sale.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    ProductReponsitory productReponsitory;
    ProductController (ProductReponsitory productReponsitory){
        this.productReponsitory = productReponsitory;
    }
    @GetMapping("/search")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR')")
    ResponsePage<Product> search(@RequestParam Map<String, String> params){
        ProductSearch productSearch = new ProductSearch();
        productSearch.setParam(params);
        return productService.search(productSearch);
    }
    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Product create(@RequestBody Product data){
        return productReponsitory.save(data);
    }
    @PutMapping("/update/{id}")
    ResponseEntity<Product> update(@PathVariable(value = "id") Integer id, @RequestBody Product data){
        Product product = productReponsitory.getById(id);
        if(product != null){
            product.setPrice(data.getPrice());
            product.setName(data.getName());
            productReponsitory.save(data);
        }else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productReponsitory.save(data), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
