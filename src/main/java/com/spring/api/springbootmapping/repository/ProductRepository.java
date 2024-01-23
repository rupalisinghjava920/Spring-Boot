package com.spring.api.springbootmapping.repository;

import com.spring.api.springbootmapping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    public Product save(Product product);




}
