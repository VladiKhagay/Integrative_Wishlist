package com.integrative.wishlistapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.integrative.wishlistapp.adapter.ShopAdapter;
import com.integrative.wishlistapp.manager.DataManager;
import com.integrative.wishlistapp.model.Product;
import com.integrative.wishlistapp.model.Shop;
import com.integrative.wishlistapp.model.activity.Instance;
import com.integrative.wishlistapp.model.activity.InvokedBy;
import com.integrative.wishlistapp.model.instance.InstanceBoundary;
import com.integrative.wishlistapp.viewmodel.ShopViewModel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopFragment extends Fragment  implements ShopAdapter.ShopActionsHandler {

    private static final String SHOP = "shop";
    private Shop shop;
    private MyApplication app;

    private RecyclerView recyclerView;
    private ShopAdapter adapter;

    private ShopViewModel shopViewModel;

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

        adapter = new ShopAdapter(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        invokeOpenShopActivity();
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

    @Override
    public void onAddClicked(Product product) {
        shopViewModel.addProductToWishlist(product);
        invokeActivity(AppConstants.ADD_CLICK, product);
    }

    @Override
    public void onRemoveClicked(Product product) {
        shopViewModel.removeProductFromWishlist(product);

        invokeActivity(AppConstants.REMOVE_CLICK, product);
    }

    private void invokeActivity (String type, Product product) {

        Instance instance = new Instance();
        Date createdTimeStamp = new Date();
        Map<String,Object> attributes = new HashMap<>();
        InvokedBy invokedBy = new InvokedBy(DataManager.getInstance().getUserBoundary().getUserId());


        List<InstanceBoundary> instanceBoundaryList = DataManager.getInstance().getInstanceBoundaries();

        for (InstanceBoundary instanceBoundary : instanceBoundaryList) {
            if (instanceBoundary.getName().equalsIgnoreCase(product.getCategory())) {
                instance.setInstanceId(instanceBoundary.getInstanceId());
            }
        }

        attributes.put(Product.class.getSimpleName(), product);


        shopViewModel.invokeActivity(type,invokedBy, instance, createdTimeStamp,attributes);

    }

    private void invokeOpenShopActivity() {
        Instance instance = new Instance();
        Date createdTimeStamp = new Date();
        Map<String,Object> attributes = new HashMap<>();
        InvokedBy invokedBy = new InvokedBy(DataManager.getInstance().getUserBoundary().getUserId());

        for (InstanceBoundary instanceBoundary : DataManager.getInstance().getInstanceBoundaries()) {
            if (instanceBoundary.getName().equals(shop.getProducts().get(0).getCategory())) {

                instance.setInstanceId(instanceBoundary.getInstanceId());
            }
        }

        attributes.put(Shop.class.getSimpleName(), shop);
        shopViewModel.invokeActivity(AppConstants.WATCH,invokedBy, instance, createdTimeStamp,attributes);
    }

}