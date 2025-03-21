package com.example.dorm_bot.repositories;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<String> productList = new ArrayList<>();

    public void addProduct(String product) {
        productList.add(product);
    }

    public void deleteProduct(int id) {
        productList.remove(id - 1);
    }

    public List<String> getAllProducts() {
        return productList;
    }
}
