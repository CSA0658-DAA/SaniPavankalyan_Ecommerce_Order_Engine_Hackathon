package com.Ecommerce_Order_Engine;

import java.util.*;

class OrderService {
    Map<Integer, Order> orders = new HashMap<>();
    int counter = 1;
    Random rand = new Random();

    void placeOrder(String user, CartServices cartService) {
        Map<Integer, CartItem> cart = cartService.getCart(user);

        if (cart.isEmpty()) {
            System.out.println(" Cart empty");
            return;
        }

        double total = 0;
        for (CartItem c : cart.values()) {
            total += c.product.price * c.qty;
        }

        Order order = new Order(counter++, cart, total);
        order.status = "PENDING PAYMENT";
        Logger.log("Order " + order.id + " created");

        if (rand.nextBoolean()) {
            order.status = "PAID";
            cartService.clearCart(user);
            System.out.println(" Order successful");
        } else {
            for (CartItem c : cart.values()) {
                c.product.stock += c.qty;
            }
            order.status = "FAILED";
            System.out.println(" Payment failed (rollback done)");
        }

        orders.put(order.id, order);
    }

   
    void cancelOrder(int orderId) {
        Order o = orders.get(orderId);
        if (o == null) {
            System.out.println(" Order not found");
            return;
        }

        if (!o.status.equals("PAID")) {
            System.out.println(" Only PAID orders can be cancelled");
            return;
        }

        for (CartItem c : o.items.values()) {
            c.product.stock += c.qty;
        }

        o.status = "CANCELLED";
        System.out.println(" Order cancelled & stock restored");
    }

    
    void returnProduct(int orderId) {
        Order o = orders.get(orderId);
        if (o == null) {
            System.out.println(" Order not found");
            return;
        }

        if (!o.status.equals("PAID")) {
            System.out.println(" Only PAID orders can be returned");
            return;
        }

        for (CartItem c : o.items.values()) {
            c.product.stock += c.qty;
        }

        o.status = "RETURNED";
        System.out.println(" Product returned & stock updated");
    }

    void viewOrders() {
        orders.values().forEach(o ->
                System.out.println("Order " + o.id + " | ₹" + o.total + " | " + o.status));
    }
}