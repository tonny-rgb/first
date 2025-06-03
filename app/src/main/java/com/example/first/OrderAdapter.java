package com.example.first;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private final List<Order> orders;

    public OrderAdapter(List<Order> orders) {
        this.orders = orders;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userIdView, totalView, itemsView;

        public ViewHolder(View view) {
            super(view);
            userIdView = view.findViewById(R.id.userIdText);
            totalView = view.findViewById(R.id.totalText);
            itemsView = view.findViewById(R.id.itemsText);
        }
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.userIdView.setText("User: " + order.getUserId());
        holder.totalView.setText("Total: Kes" + order.getTotal());

        StringBuilder itemsText = new StringBuilder("Items:\n");
        for (Product p : order.getItems()) {
            itemsText.append("• ").append(p.getName()).append(" - Kes").append(p.getPrice()).append("\n");
        }
        holder.itemsView.setText(itemsText.toString().trim());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}

