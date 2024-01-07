package com.example.sale.models;

import com.example.sale.common.SearchBase;
import com.example.sale.common.Strings;

import java.util.Map;

public class ProductSearch extends SearchBase {
    private String name;
    private Long price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setParam(Map<String, String> params){
        this.name = params.getOrDefault("name", "");
        String priceStr = params.getOrDefault("price", "");
        this.price = Strings.isNullOrEmpty(priceStr) ? Long.valueOf(priceStr) : null;
        this.setParamCommon(params);

    }
}
