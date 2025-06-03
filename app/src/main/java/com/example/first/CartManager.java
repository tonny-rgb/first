package com.example.first;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final List<Product> cartItems = new ArrayList<>();

    public static void addToCart(Product product) {
        cartItems.add(product);
    }

    public static List<Product> getCartItems() {
        return cartItems;
    }

    public static double getTotalPrice() {
        double total = 0;
        for (Product p : cartItems) {
            total += p.getPrice();
        }
        return total;
    }

    public static void clearCart() {
        cartItems.clear();
    }
}
