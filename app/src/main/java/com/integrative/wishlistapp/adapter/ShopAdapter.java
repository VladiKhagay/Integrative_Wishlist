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

    public ShopAdapter(mOnClickListener listener ) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);

        ShopViewHolder viewHolder = new ShopViewHolder(view, new mOnClickListener() {


            @Override
            public void onAddClicked(Product product) {

            }

            @Override
            public void onDeleteClicked(Product product) {

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

    class ShopViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        private mOnClickListener listener;
        TextView name;
        TextView price;
        TextView category;
        Button add;
        Button remove;
        public ShopViewHolder(@NonNull View itemView,mOnClickListener listener) {
            super(itemView);
            this.listener = listener;

            name = (TextView) itemView.findViewById(R.id.si_textview_name);
            price = (TextView) itemView.findViewById(R.id.si_textview_price);
            category = (TextView) itemView.findViewById(R.id.si_textview_category);

            add = (Button) itemView.findViewById(R.id.si_button_add);
            remove = (Button) itemView.findViewById(R.id.si_button_delete);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.si_button_add:
                    listener.onAddClicked(products.get(this.getLayoutPosition()));
                    break;
                case R.id.si_button_delete:
                    listener.onDeleteClicked(products.get(this.getLayoutPosition()));
                    break;
            }
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
