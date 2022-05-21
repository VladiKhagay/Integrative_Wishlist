package com.integrative.wishlistapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.integrative.wishlistapp.adapter.WishlistAdapter;
import com.integrative.wishlistapp.apis.InstancesService;
import com.integrative.wishlistapp.apis.RetrofitService;
import com.integrative.wishlistapp.databinding.ActivityMainBinding;
import com.integrative.wishlistapp.manager.DataManager;
import com.integrative.wishlistapp.model.Product;
import com.integrative.wishlistapp.model.Wishlist;
import com.integrative.wishlistapp.model.activity.Instance;
import com.integrative.wishlistapp.model.activity.InvokedBy;
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
import java.util.Map;

public class MainActivity extends AppCompatActivity implements WishlistAdapter.WishlistActionsHandler {

    private static final String TAG  = MainActivity.class.getSimpleName();
    private WishlistViewModel viewModel;
    private WishlistAdapter adapter;
    private MyApplication app;
    private Button goToShopButton;
    private Button goToWishlistButton;
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (MyApplication) getApplication();

        // remove top bar
        getSupportActionBar().hide();

        // Setup view elements
        RecyclerView rv = findViewById(R.id.activity_main_recycler_view);
        goToShopButton = findViewById(R.id.activity_main_button_gotoshop);
        goToWishlistButton = findViewById(R.id.activity_main_button_gotoWL);
        totalPriceTextView = findViewById(R.id.main_activity_bar_tv);

        // Setup adapter
        adapter = new WishlistAdapter(this);

        //Setup View model
        viewModel = new ViewModelProvider(this).get(WishlistViewModel.class);
        viewModel.init(app.getInstancesRepository(), app.getActivitiesRepository());

        // Setup recycler view adapter
        rv.setAdapter(adapter);


        setUpButtons();

        UserBoundary temp = DataManager.getInstance().getUserBoundary();
        if (DataManager.getInstance().getShopsMap().isEmpty()) {

            viewModel.searchWishlist(AppConstants.WISHLIST, temp.getUserId().getDomain(), temp.getUserId().getEmail(), 20, 0);
        }

        Log.d(TAG, "onCreate:: Completed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getCurrWishlistLiveData().observe(this, wishlist -> {
            Log.d("MainActivitytest", "temp user = " + wishlist.toString());
            adapter.setProducts(wishlist.getProducts());
        });

        Log.d(TAG, "OnResume:: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.UpdateChangesInDB();
        Log.d(TAG, "OnPause:: ");

    }

    private void setUpButtons() {

        goToShopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startShopActivity();
            }
        });
    }

    private void  startShopActivity() {
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);

    }


    @Override
    public void onDeleteClicked(Product product) {
        viewModel.removeProductFromWishlist(product);
        invokeActivity(AppConstants.REMOVE_CLICK, product);

    }


    private void invokeActivity (String type, Product product) {

        Instance instance = new Instance();
        Date createdTimeStamp = new Date();
        Map<String,Object> attributes = new HashMap<>();
        InvokedBy invokedBy = new InvokedBy(DataManager.getInstance().getUserBoundary().getUserId());


        List<InstanceBoundary> instanceBoundaryList = DataManager.getInstance().getInstanceBoundaries();

        for (InstanceBoundary instanceBoundary : instanceBoundaryList) {
            if (instanceBoundary.getCreatedBy().getUserId().equals(DataManager.getInstance().getUserBoundary().getUserId())) {
                instance.setInstanceId(instanceBoundary.getInstanceId());
            }
        }

        attributes.put(Product.class.getSimpleName(), product);


        viewModel.invokeActivity(type,invokedBy, instance, createdTimeStamp,attributes);
        Log.d(TAG, "invokeActivity:: Type = " + type + ", Invoked By = " + invokedBy + ", Attributes = " + attributes);

    }
}