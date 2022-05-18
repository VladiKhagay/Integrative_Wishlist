package com.integrative.wishlistapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.integrative.wishlistapp.adapter.ShopAdapter;
import com.integrative.wishlistapp.adapter.ShopFragmentAdapter;
import com.integrative.wishlistapp.model.Product;
import com.integrative.wishlistapp.model.Shop;
import com.integrative.wishlistapp.model.Wishlist;
import com.integrative.wishlistapp.repository.ActivitiesRepository;
import com.integrative.wishlistapp.repository.InstancesRepository;
import com.integrative.wishlistapp.viewmodel.ShopViewModel;
import com.integrative.wishlistapp.viewmodel.WishlistViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopFragment extends Fragment {

    private static final String SHOP = "shop";
    private Shop shop;
    private MyApplication app;

    private RecyclerView recyclerView;
    private ShopAdapter adapter;

    private ShopViewModel shopViewModel;
    private mOnClickListener listener;

    public ShopFragment() {
        // Required empty public constructor
    }


    public static Fragment newInstance(Shop shop) {
        ShopFragment fragment = new ShopFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SHOP, shop);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.shop = (Shop) getArguments().get(SHOP);
        }


        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);

        this.listener = new mOnClickListener() {
            @Override
            public void onAddClicked(Product product) {
                shopViewModel.addProductToWishlist(product);
            }

            @Override
            public void onDeleteClicked(Product product) {
                shopViewModel.removeProductFromWishlist(product);
            }
        };
        adapter = new ShopAdapter(listener);

    }

    @SuppressLint("StringFormatInvalid")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        recyclerView = view.findViewById(R.id.shop_fragment_recycler_view);
        recyclerView.setAdapter(adapter);
        adapter.setProducts(this.shop.getProducts());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}