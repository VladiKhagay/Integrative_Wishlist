package com.integrative.wishlistapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.integrative.wishlistapp.manager.DataManager;
import com.integrative.wishlistapp.model.Product;
import com.integrative.wishlistapp.model.Shop;
import com.integrative.wishlistapp.model.activity.ActivityBoundary;
import com.integrative.wishlistapp.model.activity.Instance;
import com.integrative.wishlistapp.model.activity.InvokedBy;
import com.integrative.wishlistapp.model.instance.InstanceBoundary;
import com.integrative.wishlistapp.model.instance.InstanceId;
import com.integrative.wishlistapp.model.user.UserId;
import com.integrative.wishlistapp.repository.ActivitiesRepository;
import com.integrative.wishlistapp.repository.InstancesRepository;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ShopViewModel extends AndroidViewModel {
    private InstancesRepository repository;
    private ActivitiesRepository activitiesRepository;
    private MutableLiveData<Map<String,Shop>> shops;
    private ActivityBoundary activityBoundary;

    private Gson gson;
    private Type mapType;

    public ShopViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(InstancesRepository repository, ActivitiesRepository activitiesRepository) {
        this.repository = repository;
        this.activitiesRepository = activitiesRepository;
        this.shops = repository.getShops();
        this.activityBoundary = new ActivityBoundary();

        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
                .create();
        mapType = new TypeToken<Map<String,Object>>(){}.getType();
    }

    public void retrieveShops (String type, String userDomain, String userEmail, int size, int page) {
        this.repository.retrieveShops(type, userDomain, userEmail, size, page);
    }


    public void addProductToWishlist(Product product) {
        repository.addProductToWishlist(product);
    }

    public void removeProductFromWishlist(Product product ) {
        repository.removeProductFromWishlist(product);
    }
    public MutableLiveData<Map<String, Shop>> getShops() {
        return shops;
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
