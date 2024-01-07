package com.example.sale.service;

import com.example.sale.entity.Product;
import com.example.sale.models.ProductSearch;
import com.example.sale.reponsitory.ProductReponsitory;
import com.example.sale.response.ResponsePage;
import com.google.gson.Gson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductReponsitory productReponsitory;

    public ProductService(ProductReponsitory productReponsitory) {
        this.productReponsitory = productReponsitory;
    }


    public ResponsePage<Product> search(ProductSearch productSearch){
        Pageable pageable = PageRequest.of(productSearch.getPage(), productSearch.getLimit(), Sort.by("name"));
        Page<Product> productPage = this.productReponsitory.search(productSearch.getName(),productSearch.getPrice(), pageable);
        ResponsePage<Product> responsePage = new ResponsePage<>();
        responsePage.setBody(productPage.getContent());
        responsePage.setTotalElements(productPage.getTotalElements());
        return responsePage;
    }
}
