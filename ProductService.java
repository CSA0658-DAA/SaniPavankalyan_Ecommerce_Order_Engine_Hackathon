package com.Ecommerce_Order_Engine;

import java.util.*;

class ProductService {
    Map<Integer, Product> products = new HashMap<>();

    void addProduct(int id, String name, int stock, double price) {
        if (products.containsKey(id)) {
            System.out.println(" Duplicate ID!");
            return;
        }
        if (stock < 0) {
            System.out.println(" Invalid stock!");
            return;
        }
        products.put(id, new Product(id, name, stock, price));
        System.out.println(" Product added!");
    }

    void viewProducts() {
        products.values().forEach(p ->
                System.out.println(p.id + " | " + p.name + " | Stock=" + p.stock + " | ₹" + p.price));
    }

    Product getProduct(int id) {
        return products.get(id);
    }

    void lowStockAlert() {
        products.values().stream()
                .filter(p -> p.stock < 5)
                .forEach(p ->
                        System.out.println(" Low stock: " + p.name + " (" + p.stock + ")"));
    }
}