package com.spring.api.springbootmapping.controller;

import com.spring.api.springbootmapping.entity.Barend;
import com.spring.api.springbootmapping.entity.Product;
import com.spring.api.springbootmapping.repository.BarendRepository;
import com.spring.api.springbootmapping.repository.ProductRepository;
import com.spring.api.springbootmapping.request.ProductRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BarendRepository barendRepository;

    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody ProductRequest productRequest){
        Barend bar=new Barend();
        bar.setProdName(productRequest.getBarend());
        bar.setQuality(productRequest.getBarend());
        bar=barendRepository.save(bar);
        Product product =new Product(productRequest);
        product.setBarend(bar);
        product=productRepository.save(product);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }



}
