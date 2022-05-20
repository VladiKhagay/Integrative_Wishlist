package com.integrative.wishlistapp;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

import com.integrative.wishlistapp.apis.ActivitiesService;
import com.integrative.wishlistapp.apis.InstancesService;
import com.integrative.wishlistapp.apis.RetrofitService;
import com.integrative.wishlistapp.apis.UsersService;
import com.integrative.wishlistapp.model.Shop;
import com.integrative.wishlistapp.model.User;
import com.integrative.wishlistapp.model.Wishlist;
import com.integrative.wishlistapp.repository.ActivitiesRepository;
import com.integrative.wishlistapp.repository.InstancesRepository;
import com.integrative.wishlistapp.repository.UsersRepository;
import com.integrative.wishlistapp.viewmodel.ShopViewModel;
import com.integrative.wishlistapp.viewmodel.WishlistViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private InstancesService instancesService;
    private UsersService usersService;
    private ActivitiesService activitiesService;

    private InstancesRepository instancesRepository;
    private UsersRepository usersRepository;
    private ActivitiesRepository activitiesRepository;




    @Override
    public void onCreate() {
        super.onCreate();

        instancesService = RetrofitService.getInstance().create(InstancesService.class);
        usersService = RetrofitService.getInstance().create(UsersService.class);
        activitiesService = RetrofitService.getInstance().create(ActivitiesService.class);

        instancesRepository = new InstancesRepository(instancesService);
        usersRepository = new UsersRepository(usersService);
        activitiesRepository = new ActivitiesRepository(activitiesService);


    }

    public InstancesRepository getInstancesRepository() {
        return instancesRepository;
    }

    public UsersRepository getUsersRepository() {
        return usersRepository;
    }

    public ActivitiesRepository getActivitiesRepository() {
        return activitiesRepository;
    }


}
