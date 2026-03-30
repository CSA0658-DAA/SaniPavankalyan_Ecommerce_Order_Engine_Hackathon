package com.Ecommerce_Order_Engine;

import java.util.concurrent.locks.ReentrantLock;

class Product {
    int id;
    String name;
    int stock;
    double price;
    ReentrantLock lock = new ReentrantLock();

    Product(int id, String name, int stock, double price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }
}