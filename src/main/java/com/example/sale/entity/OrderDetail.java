package com.example.sale.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    private Integer productQuantity;
    private Integer total;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",nullable = false)
    private Order orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    private Product productId;

    public Integer getId() {
        return id;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public Integer getTotal() {
        return total;
    }

}
