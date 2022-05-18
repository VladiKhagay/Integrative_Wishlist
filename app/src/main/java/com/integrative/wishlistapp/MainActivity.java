package com.integrative.wishlistapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.integrative.wishlistapp.adapter.WishlistAdapter;
import com.integrative.wishlistapp.apis.InstancesService;
import com.integrative.wishlistapp.apis.RetrofitService;
import com.integrative.wishlistapp.manager.DataManager;
import com.integrative.wishlistapp.model.Product;
import com.integrative.wishlistapp.model.Wishlist;
import com.integrative.wishlistapp.model.instance.CreatedBy;
import com.integrative.wishlistapp.model.instance.InstanceBoundary;
import com.integrative.wishlistapp.model.instance.InstanceId;
import com.integrative.wishlistapp.model.instance.Location;
import com.integrative.wishlistapp.model.user.UserBoundary;
import com.integrative.wishlistapp.model.user.UserId;
import com.integrative.wishlistapp.repository.InstancesRepository;
import com.integrative.wishlistapp.viewmodel.WishlistViewModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WishlistViewModel viewModel;
    private WishlistAdapter adapter;
    private MyApplication app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (MyApplication) getApplication();
        getSupportActionBar().hide();

        adapter = new WishlistAdapter();
        viewModel = new ViewModelProvider(this).get(WishlistViewModel.class);

        RecyclerView rv = findViewById(R.id.activity_main_recycler_view);
        rv.setAdapter(adapter);
        viewModel.init(app.getInstancesRepository());
        UserBoundary temp = DataManager.getInstance().getUserBoundary();
        viewModel.searchWishlist("Wishlist",temp.getUserId().getDomain(), temp.getUserId().getEmail(), 10,0);

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getWishlistLiveData().observe(this, wishlists -> {
            if (wishlists != null) {
                DataManager.getInstance().setWishlists(wishlists);
                DataManager.getInstance().setCurrentWishlist(DataManager.getInstance().getWishlists().get(0));
                Log.d("MAINTEST", " TEST:: " + DataManager.getInstance().getCurrentWishlist().toString());
                adapter.setProducts(DataManager.getInstance().getCurrentWishlist().getProducts());
            }
        });
    }
}