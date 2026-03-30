package com.Ecommerce_Order_Engine;

import java.util.*;

class CartServices {
    Map<String, Map<Integer, CartItem>> carts = new HashMap<>();
    ProductService productService;
    double discount = 0;

    CartServices(ProductService productService) {
        this.productService = productService;
    }

    Map<Integer, CartItem> getCart(String user) {
        return carts.computeIfAbsent(user, k -> new HashMap<>());
    }

    void addToCart(String user, Product p, int qty) {
        if (p == null) {
            System.out.println(" Product not found");
            return;
        }

        p.lock.lock();
        try {
            if (p.stock < qty) {
                System.out.println(" Not enough stock");
                return;
            }
            p.stock -= qty;
            getCart(user).put(p.id, new CartItem(p, qty));
            Logger.log(user + " added " + p.name);
            System.out.println(" Added to cart");
        } finally {
            p.lock.unlock();
        }
    }

    void applyCoupon(String code) {
        if (code.equals("SAVE10")) {
            discount = 0.10;
            System.out.println(" 10% discount applied");
        } else {
            System.out.println(" Invalid coupon");
        }
    }

    double getDiscount() {
        return discount;
    }

    void removeFromCart(String user, int pid) {
        CartItem item = getCart(user).remove(pid);
        if (item != null) {
            item.product.stock += item.qty;
            System.out.println(" Removed");
        }
    }

    void viewCart(String user) {
        Map<Integer, CartItem> cart = getCart(user);
        if (cart.isEmpty()) {
            System.out.println("Cart empty");
            return;
        }

        double total = 0;
        for (CartItem i : cart.values()) {
            double price = i.product.price * i.qty;
            total += price;
            System.out.println(i.product.name + " x " + i.qty + " = ₹" + price);
        }

        if (discount > 0) {
            double disc = total * discount;
            total -= disc;
            System.out.println("Discount: -" + disc);
        }

        System.out.println("Total: ₹" + total);
    }

    void clearCart(String user) {
        carts.remove(user);
        discount = 0;
    }
}