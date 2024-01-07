package com.example.sale.reponsitory;

import com.example.sale.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReponsitory extends JpaRepository<Product, Integer> {

    @Query(value = "select * from products where (:name = '' or name like %:name%) and (:price is null or price <= :price)",
            countQuery = "select count(*) from products where (:name = '' or name like %:name%) and (:price is null or price <= :price)",
            nativeQuery = true)
    Page<Product> search(String name, Long price, Pageable pageable);
}
