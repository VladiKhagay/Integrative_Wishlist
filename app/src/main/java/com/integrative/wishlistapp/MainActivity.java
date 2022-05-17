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
import com.integrative.wishlistapp.model.Wishlist;
import com.integrative.wishlistapp.repository.InstancesRepository;
import com.integrative.wishlistapp.viewmodel.WishlistViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WishlistViewModel viewModel;
    private  WishlistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        InstancesService instancesService = RetrofitService.getInstance().create(InstancesService.class);

        InstancesRepository instancesRepository = new InstancesRepository(instancesService);


        adapter = new WishlistAdapter();
        viewModel = new ViewModelProvider(this).get(WishlistViewModel.class);

        RecyclerView rv = findViewById(R.id.recycler_view);
        rv.setAdapter(adapter);
        viewModel.init(instancesRepository);
        viewModel.searchWishlist("dummytype","2022b.timor.bystritskie", "t@t.zain",10,0);


        viewModel.getWishlistLiveData().observe(this, new Observer<List<Wishlist>>() {
            @Override
            public void onChanged(List<Wishlist> wishlists) {
                adapter.setProducts(wishlists.get(0).getProducts());
                Log.d("MainActivity Test", wishlists.toString());
            }
        });


    }

}