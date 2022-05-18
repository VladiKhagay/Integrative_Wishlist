package com.integrative.wishlistapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.integrative.wishlistapp.model.Shop;
import com.integrative.wishlistapp.model.instance.InstanceBoundary;
import com.integrative.wishlistapp.repository.InstancesRepository;

import java.util.List;

public class ShopViewModel extends AndroidViewModel {
    private InstancesRepository repository;
    private MutableLiveData<List<Shop>> shops;

    public ShopViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(InstancesRepository repository) {
        this.repository = repository;
        this.shops = repository.getShops();
    }

    public void retrieveShops (String type, String userDomain, String userEmail, int size, int page) {
        this.repository.retrieveShops(type, userDomain, userEmail, size, page);
    }

    public LiveData<List<Shop>> getShops() {
        return shops;
    }
}
