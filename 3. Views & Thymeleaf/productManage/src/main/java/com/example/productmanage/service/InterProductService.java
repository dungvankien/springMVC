package com.example.productmanage.service;

import com.example.productmanage.model.Product;

import java.util.List;

public interface InterProductService {
    List<Product> findAll();

    void save(Product product);

    Product findById(int id);

    Product findByName(String name);

    void update(int id, Product product);

    void remove(int id);
}
