package com.integrative.wishlistapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.integrative.wishlistapp.model.Wishlist;
import com.integrative.wishlistapp.model.instance.InstanceBoundary;
import com.integrative.wishlistapp.repository.InstancesRepository;

import java.util.List;

public class WishlistViewModel extends AndroidViewModel {

    private InstancesRepository repository;
    private LiveData<List<InstanceBoundary>> instances;
    private LiveData<List<Wishlist>> wishlistsLiveData;
    private LiveData<InstanceBoundary> createdInstance;
    private LiveData<InstanceBoundary> updatedInstance;

    public WishlistViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(InstancesRepository repository) {
        this.repository = repository;
        this.wishlistsLiveData = repository.getWishlists();
        this.createdInstance = repository.getCreatedInstance();
        this.updatedInstance = repository.getUpdatedInstance();
    }

    public void searchWishlist(String type, String userDomain, String userEmail, int size, int page) {
        repository.retrieveWishlist(type, userDomain, userEmail, size, page);
    }

    public LiveData<List<Wishlist>> getWishlistLiveData() {
        return this.wishlistsLiveData;

    }

    public void createInstance (InstanceBoundary inputBoundary) {
        repository.createInstance(inputBoundary);
    }

    public LiveData<InstanceBoundary> getCreatedInstance() {
        return this.createdInstance;
    }

    public void updateInstance (InstanceBoundary inputBoundary) {
        repository.createInstance(inputBoundary);
    }

    public LiveData<InstanceBoundary> getUpdatedInstance() {
        return this.updatedInstance;
    }


}
