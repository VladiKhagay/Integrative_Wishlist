package com.integrative.wishlistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.integrative.wishlistapp.adapter.ShopFragmentAdapter;
import com.integrative.wishlistapp.apis.InstancesService;
import com.integrative.wishlistapp.apis.RetrofitService;
import com.integrative.wishlistapp.manager.DataManager;
import com.integrative.wishlistapp.model.Product;
import com.integrative.wishlistapp.model.Shop;
import com.integrative.wishlistapp.model.activity.Instance;
import com.integrative.wishlistapp.model.activity.InvokedBy;
import com.integrative.wishlistapp.model.instance.InstanceBoundary;
import com.integrative.wishlistapp.model.user.UserBoundary;
import com.integrative.wishlistapp.repository.InstancesRepository;
import com.integrative.wishlistapp.viewmodel.ShopViewModel;
import com.integrative.wishlistapp.viewmodel.WishlistViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private List<Shop> shopsList;
    private ShopViewModel shopViewModel;
    private Button goToWishlistButton;

    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        app = (MyApplication) getApplication();

        getSupportActionBar().hide();


        goToWishlistButton = findViewById(R.id.shop_activity_button_back);
        shopsList = new ArrayList<>();

        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        shopViewModel.init(app.getInstancesRepository(), app.getActivitiesRepository());

        tabLayout = findViewById(R.id.shop_activity_tab_layout);
        viewPager2 = findViewById(R.id.shop_activity_viewpager);
        progressBar = findViewById(R.id.shop_activity_progressbar);

        initViews();
        setUpButtons();

        UserBoundary userBoundary = DataManager.getInstance().getUserBoundary();
        if (DataManager.getInstance().getShopsMap().isEmpty()) {
            shopViewModel.retrieveShops(AppConstants.SHOP, userBoundary.getUserId().getDomain(), userBoundary.getUserId().getEmail(), 15, 0);
        }
    }

    private void initViews() {
        shopViewModel.getShops().observe(this, stringShopMap -> {
            if (stringShopMap != null && !stringShopMap.isEmpty()) {
                shopsList.addAll(stringShopMap.values());
                viewPager2.setAdapter(new ShopFragmentAdapter(this, stringShopMap.size(), shopsList));

                new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        if (position < shopsList.size()) {
                            if (shopsList.get(position).getProducts().get(0).getCategory() != null) {
                                tab.setText(shopsList.get(position).getProducts().get(0).getCategory());
                            }
                        }

                    }
                }).attach();
                progressBar.setVisibility(View.GONE);

            }
        });
    }

    private void setUpButtons() {
        goToWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWishlistActivity();
            }
        });
    }

    private void startWishlistActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}