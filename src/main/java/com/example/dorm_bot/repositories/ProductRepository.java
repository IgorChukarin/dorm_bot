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

    public void deleteProductById(int id) {
        productList.remove(id - 1);
    }

    public boolean removeProductByName(String name) {
        if (productList.contains(name)) {
            productList.remove(name);
            return true;
        } else
            return false;
    }

    public List<String> getAllProducts() {
        return productList;
    }
}
