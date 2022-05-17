package com.integrative.wishlistapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.integrative.wishlistapp.R;
import com.integrative.wishlistapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {

    public WishlistAdapter() {
    }

    private List<Product> products = new ArrayList<>();
    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);

        return new WishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {

        Product product = products.get(position);

        holder.wishList_LBL_productName.setText(product.getName());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts (List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }


    class WishlistViewHolder extends RecyclerView.ViewHolder {
        private TextView wishList_LBL_productName;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);

            wishList_LBL_productName = itemView.findViewById(R.id.wishList_LBL_productName);
        }
    }
}
