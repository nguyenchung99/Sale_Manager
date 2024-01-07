package com.example.sale.response;

import java.util.List;

public class ResponsePage<T> {
    private List<T> body;
    private Long totalElements;

    public List<T> getBody() {
        return body;
    }

    public void setBody(List<T> body) {
        this.body = body;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }
}
