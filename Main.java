package com.Ecommerce_Order_Engine;

import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ProductService ps = new ProductService();
        CartServices cs = new CartServices(ps);
        OrderService os = new OrderService();

        String user = "user1";

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Add to Cart");
            System.out.println("4. Remove from Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Apply Coupon");
            System.out.println("7. Place Order");
            System.out.println("8. Cancel Order");
            System.out.println("9. View Orders");
            System.out.println("10. Low Stock Alert");
            System.out.println("11. Return Product");
            System.out.println("12. Simulate Concurrent Users");
            System.out.println("13. View Logs");
            System.out.println("14. Trigger Failure Mode");
            System.out.println("0. Exit");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Enter id name stock price: ");
                    ps.addProduct(sc.nextInt(), sc.next(), sc.nextInt(), sc.nextDouble());
                    break;

                case 2:
                    ps.viewProducts();
                    break;

                case 3:
                    System.out.print("Enter productId qty: ");
                    cs.addToCart(user, ps.getProduct(sc.nextInt()), sc.nextInt());
                    break;

                case 4:
                    System.out.print("Enter productId: ");
                    cs.removeFromCart(user, sc.nextInt());
                    break;

                case 5:
                    cs.viewCart(user);
                    break;

                case 6:
                    System.out.print("Enter coupon: ");
                    cs.applyCoupon(sc.next());
                    break;

                case 7:
                    os.placeOrder(user, cs);
                    break;

                case 8:
                    System.out.print("Enter orderId: ");
                    os.cancelOrder(sc.nextInt());
                    break;

                case 9:
                    os.viewOrders();
                    break;

                case 10:
                    ps.lowStockAlert();
                    break;

                case 11:
                    System.out.print("Enter orderId: ");
                    os.returnProduct(sc.nextInt());
                    break;

                case 12:
                    simulate(ps);
                    break;

                case 13:
                    Logger.show();
                    break;

                case 14:
                    throw new RuntimeException(" Failure triggered");

                case 0:
                    System.exit(0);
            }
        }
    }

    static void simulate(ProductService ps) {
        Product p = new Product(999, "Test", 5, 100);
        ps.products.put(999, p);

        Runnable t1 = () -> buy("UserA", p);
        Runnable t2 = () -> buy("UserB", p);

        new Thread(t1).start();
        new Thread(t2).start();
    }

    static void buy(String user, Product p) {
        p.lock.lock();
        try {
            if (p.stock >= 5) {
                p.stock -= 5;
                System.out.println(user + " SUCCESS");
            } else {
                System.out.println(user + " FAILED");
            }
        } finally {
            p.lock.unlock();
        }
    }
}