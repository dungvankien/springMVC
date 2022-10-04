package com.example.productmanage.service;

import com.example.productmanage.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements InterProductService{
    private static final Map<Integer,Product> products;
    static {
        products = new HashMap<>();
        products.put(1, new Product(1,"Trà Chanh", 12000, "chua ngot","URC"));
        products.put(2, new Product(2,"Trà Chanh", 12000, "chua ngot","URC"));
        products.put(3, new Product(3,"Trà Chanh", 12000, "chua ngot","URC"));
        products.put(4, new Product(4,"Trà Chanh", 12000, "chua ngot","URC"));
        products.put(5, new Product(5,"Trà Chanh", 12000, "chua ngot","URC"));
        products.put(6, new Product(6,"Trà Chanh", 12000, "chua ngot","URC"));

    }
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(),product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public Product findByName(String name) {
        return products.get(name);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id,product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }
}
