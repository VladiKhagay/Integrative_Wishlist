package com.integrative.wishlistapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.integrative.wishlistapp.adapter.WishlistAdapter;
import com.integrative.wishlistapp.apis.InstancesService;
import com.integrative.wishlistapp.apis.RetrofitService;
import com.integrative.wishlistapp.model.Product;
import com.integrative.wishlistapp.model.Wishlist;
import com.integrative.wishlistapp.model.instance.InstanceBoundary;
import com.integrative.wishlistapp.repository.InstancesRepository;
import com.integrative.wishlistapp.viewmodel.WishlistViewModel;

import java.util.ArrayList;
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
        viewModel.searchWishlist("SHOP","2022b.timor.bystritskie", "Dima@gogo.com",100,0);

////        instancesRepository.createInstance();
//
//        String json = "{ \"instanceId\": { \"domain\": \"2022b.timor.bystritskie\", \"id\": \"\" }, \"type\": \"dummytype\", \"name\": \"new instance\", \"active\": true, \"createdTimestamp\": \"2022-01-06 17:12:10\", \"createdBy\": { \"userId\": { \"domain\": \"2022b.timor.bystritskie\", \"email\": \"t1@t.zain\" } }, \"location\": { \"lat\": 32.115139, \"lng\": 34.817804 }, \"instanceAttributes\": { \"products\": [ { \"name\": \"Sushi - Party Plate\", \"price\": 62, \"category\": \"food\" }, { \"name\": \"Abibas T-Shirt\", \"price\": 89.70, \"category\": \"clothing\" }, { \"name\": \"Abibas Hat\", \"price\": 35.70, \"category\": \"clothing\" }, { \"name\": \"Playbox 2\", \"price\": 399, \"category\": \"electronics\" } ], \"description\": \"This is what I want for my birthday next week.\" } }";
//
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//
//        InstanceBoundary newIn = gson.fromJson(json, InstanceBoundary.class);
//        viewModel.createInstance(newIn);
//
//        ArrayList<Product> p = new ArrayList<>();
//        p.add(new Product("test", 0.2, "ofir"));
//        p.add(new Product("test2", 0., "vladi"));
//        newIn.getInstanceAttributes().put("products", p);
//
//        Log.d("ASDF", newIn.getCreatedTimestamp().toString());
//        viewModel.updateInstance(newIn);

        List<Wishlist> wishlists;
        viewModel.getWishlistLiveData().observe(this, new Observer<List<Wishlist>>() {
            @Override
            public void onChanged(List<Wishlist> wishlists) {
                adapter.setProducts(wishlists.get(0).getProducts());
                Log.d("MainActivity Test", wishlists.toString());

            }
        });

        viewModel.getCreatedInstance().observe(this, new Observer<InstanceBoundary>() {
            @Override
            public void onChanged(InstanceBoundary instanceBoundary) {
                Log.d("MainActivity", "Created instance: " + instanceBoundary.toString());
            }
        });

        viewModel.getUpdatedInstance().observe(this, new Observer<InstanceBoundary>() {
            @Override
            public void onChanged(InstanceBoundary instanceBoundary) {
                Log.d("MainActivity", "Upddated instnace: " + instanceBoundary.toString());
            }
        });





    }

}