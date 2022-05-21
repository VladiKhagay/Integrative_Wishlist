package com.integrative.wishlistapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.integrative.wishlistapp.apis.ActivitiesService;
import com.integrative.wishlistapp.manager.DataManager;
import com.integrative.wishlistapp.model.Product;
import com.integrative.wishlistapp.model.Wishlist;
import com.integrative.wishlistapp.model.activity.ActivityBoundary;
import com.integrative.wishlistapp.model.activity.Instance;
import com.integrative.wishlistapp.model.activity.InvokedBy;
import com.integrative.wishlistapp.model.instance.InstanceBoundary;
import com.integrative.wishlistapp.repository.ActivitiesRepository;
import com.integrative.wishlistapp.repository.InstancesRepository;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.converter.gson.GsonConverterFactory;

public class WishlistViewModel extends AndroidViewModel {

    private InstancesRepository repository;
    private LiveData<Map<String,Wishlist>> wishlistsLiveData;
    private LiveData<Wishlist> currWishlistLiveData;
    private ActivityBoundary activityBoundary;
    private ActivitiesRepository activitiesRepository;
    private Gson gson;
    private Type mapType;


    public WishlistViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(InstancesRepository repository, ActivitiesRepository activitiesRepository) {
        this.repository = repository;
        this.activitiesRepository = activitiesRepository;
        this.wishlistsLiveData = repository.getWishlists();
        this.currWishlistLiveData = repository.getCurrentWishList();
        this.activityBoundary = new ActivityBoundary();
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
                .create();
        mapType = new TypeToken<Map<String,Object>>(){}.getType();

    }


    public void searchWishlist(String type, String userDomain, String userEmail, int size, int page) {
        repository.retrieveWishlists(type, userDomain, userEmail, size, page);
    }


    public void createInstance(InstanceBoundary inputBoundary) {
        repository.createInstance(inputBoundary);
    }



    public void updateInstance(InstanceBoundary inputBoundary) {
        repository.createInstance(inputBoundary);
    }

    public LiveData<Map<String, Wishlist>> getWishlistsLiveData() {
        return wishlistsLiveData;
    }

    public LiveData<Wishlist> getCurrWishlistLiveData() {
        return currWishlistLiveData;
    }

    public void removeProductFromWishlist(Product product) {
        this.repository.removeProductFromWishlist(product);
    }

    public void invokeActivity (String type, InvokedBy invokedBy, Instance instance, Date createdTimeStamp, Map<String,Object> attributes) {

        this.activityBoundary.setType(type);
        this.activityBoundary.setInvokedBy(invokedBy);
        this.activityBoundary.setInstance(instance);
        this.activityBoundary.setCreatedTimestamp(createdTimeStamp);
        this.activityBoundary.setActivityAttributes(attributes);
        this.activitiesRepository.invoke(this.activityBoundary);

    }

    public void UpdateChangesInDB() {

        String wishlistJson =gson.toJson(DataManager.getInstance().getCurrentWishlist());
        Map<String , Object> wishlistAsMap = gson.fromJson(wishlistJson, mapType);
        DataManager.getInstance().getActiveInstance().setInstanceAttributes(wishlistAsMap);

        repository.updateInstance(DataManager.getInstance().getActiveInstance());
    }
}
