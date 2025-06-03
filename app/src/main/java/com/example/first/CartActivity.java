package com.example.first;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView totalPriceTextView;
    private Button checkoutButton;
    private List<Product> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cartRecyclerView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        checkoutButton = findViewById(R.id.checkoutButton);

        cartItems = CartManager.getCartItems();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ProductAdapter(cartItems)); // Reusing adapter

        double total = cartItems.stream().mapToDouble(Product::getPrice).sum();
        totalPriceTextView.setText("Total: $" + String.format("%.2f", total));

        checkoutButton.setOnClickListener(v -> saveOrderToRealtimeDB(cartItems, total));
    }

    private void saveOrderToRealtimeDB(List<Product> items, double totalAmount) {
        String userId = "guest_" + UUID.randomUUID().toString(); // Simulated user
        String orderId = UUID.randomUUID().toString();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> order = new HashMap<>();
        order.put("userId", userId);
        order.put("items", items);
        order.put("total", totalAmount);

        dbRef.child("orders").child(orderId).setValue(order)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this, "Order placed!", Toast.LENGTH_SHORT).show();
                    CartManager.clearCart();
                    finish();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
