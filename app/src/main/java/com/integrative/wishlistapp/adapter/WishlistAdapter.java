package com.integrative.wishlistapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.integrative.wishlistapp.R;
import com.integrative.wishlistapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {

    public interface WishlistActionsHandler {
        void onDeleteClicked(Product product);
    }

    private static final String TAG = WishlistAdapter.class.getSimpleName();
    private List<Product> products = new ArrayList<>();
    private WishlistActionsHandler actionsHandler;


    public WishlistAdapter(WishlistActionsHandler actionsHandler ) {
        this.actionsHandler = actionsHandler;
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item, parent, false);

        WishlistViewHolder viewHolder = new WishlistViewHolder(view);

        viewHolder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actionsHandler.onDeleteClicked(products.get(viewHolder.getLayoutPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {

        Product product = products.get(position);

        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice().toString());
        holder.category.setText(product.getCategory());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts (List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }


    class WishlistViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView price;
        private TextView category;
        private Button delete_button;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.wli_textview_name);
            price = (TextView) itemView.findViewById(R.id.wli_textview_price);
            category = (TextView) itemView.findViewById(R.id.wli_textview_category);
            delete_button = (Button) itemView.findViewById(R.id.wli_button_delete);
        }

    }
}
