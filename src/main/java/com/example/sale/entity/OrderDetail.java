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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
