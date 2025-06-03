package com.example.first;

import java.util.List;

public class Order {
    private String userId;
    private List<Product> items;
    private double total;

    public Order() {} // Needed for Firebase

    public String getUserId() { return userId; }
    public List<Product> getItems() { return items; }
    public double getTotal() { return total; }
}

