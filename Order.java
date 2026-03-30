package com.Ecommerce_Order_Engine;

import java.util.*;

class Order {
    int id;
    Map<Integer, CartItem> items;
    double total;
    String status;

    Order(int id, Map<Integer, CartItem> items, double total) {
        this.id = id;
        this.items = new HashMap<>(items);
        this.total = total;
        this.status = "CREATED";
    }
}