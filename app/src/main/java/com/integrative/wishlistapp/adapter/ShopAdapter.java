package com.integrative.wishlistapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.integrative.wishlistapp.R;
import com.integrative.wishlistapp.mOnClickListener;
import com.integrative.wishlistapp.model.Product;
import com.integrative.wishlistapp.viewmodel.ShopViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter  extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    private List<Product> products = new ArrayList<>();
    private mOnClickListener listener;

    public  interface ShopActionsHandler{
        void onAddClicked (Product product);
        void onRemoveClicked(Product product);
    }

    private ShopActionsHandler shopActionsHandler;

    public ShopAdapter(ShopActionsHandler shopActionsHandler) {
        this.shopActionsHandler = shopActionsHandler;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);

        ShopViewHolder viewHolder = new ShopViewHolder(view);
        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopActionsHandler.onAddClicked(products.get(viewHolder.getLayoutPosition()));
            }
        });

        viewHolder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopActionsHandler.onRemoveClicked(products.get(viewHolder.getLayoutPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position){
        Product product = products.get(position);
        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice().toString());
        holder.category.setText(product.getCategory());
        holder.remove.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ShopViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView price;
        TextView category;
        Button add;
        Button remove;
        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.si_textview_name);
            price = (TextView) itemView.findViewById(R.id.si_textview_price);
            category = (TextView) itemView.findViewById(R.id.si_textview_category);

            add = (Button) itemView.findViewById(R.id.si_button_add);
            remove = (Button) itemView.findViewById(R.id.si_button_delete);

        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
