# SaniPavankalyan_Ecommerce_Order_Engine_Hackathon 
1.This is a Core Java E-commerce system using OOP concepts and collections.
2.Product class stores product details like id, name, price, and stock with thread safety using ReentrantLock.
3.Used ReentrantLock to handle concurrency and avoid stock inconsistency when multiple users access the same product.
4.ProductService manages products (add, view, low stock alerts).
5.CartServices handles user carts using HashMap and supports add/remove/view items.
6.It also supports coupon logic with discount calculation (e.g., SAVE10 for 10% discount).
7.OrderService processes orders, calculates total, and simulates payment success/failure.
8.It also supports cancel order and return product with stock updates.
9.Logger stores system logs with timestamps for tracking actions.
10.Main class provides a CLI menu (1–14 options) to interact with the entire system including concurrency simulation and failure mode.
