package com.Ecommerce_Order_Engine;

import java.util.*;

class Logger {
    static List<String> logs = new ArrayList<>();

    static void log(String msg) {
        logs.add("" + new Date() + " " + msg);
    }

    static void show() {
        logs.forEach(System.out::println);
    }
}