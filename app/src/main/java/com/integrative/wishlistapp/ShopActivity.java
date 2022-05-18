package com.integrative.wishlistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.integrative.wishlistapp.adapter.ShopFragmentAdapter;
import com.integrative.wishlistapp.apis.InstancesService;
import com.integrative.wishlistapp.apis.RetrofitService;
import com.integrative.wishlistapp.model.Shop;
import com.integrative.wishlistapp.repository.InstancesRepository;
import com.integrative.wishlistapp.viewmodel.ShopViewModel;
import com.integrative.wishlistapp.viewmodel.WishlistViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private List<Shop> shopsList;
    private ShopViewModel shopViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        getSupportActionBar().hide();
        shopsList = new ArrayList<>();

        InstancesService instancesService = RetrofitService.getInstance().create(InstancesService.class);
        InstancesRepository instancesRepository = new InstancesRepository(instancesService);

        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);


        shopViewModel.init(instancesRepository);
        instancesRepository.getAllInstances().observe(this, boundaryList -> {});


        tabLayout = findViewById(R.id.shop_activity_tab_layout);
        viewPager2 = findViewById(R.id.shop_activity_viewpager);
        progressBar = findViewById(R.id.shop_activity_progressbar);

        shopViewModel.retrieveShops("SHOP", "2022b.timor.bystritskie", "Dima@gogo.com", 10, 0);
        initViews();



    }

    private void initViews () {
        shopViewModel.getShops().observe(this, shops -> {

            if (shops != null && shops.size() > 0 ) {
                shopsList.addAll(shops);
                viewPager2.setAdapter(new ShopFragmentAdapter(this, shopsList.size(), shopsList));

                new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        if (shops.get(position).getProducts().get(0).getCategory() != null) {
                            tab.setText(shops.get(position).getProducts().get(0).getCategory());
                        }
                    }
                }).attach();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}