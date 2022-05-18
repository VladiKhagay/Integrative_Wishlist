package com.integrative.wishlistapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.integrative.wishlistapp.model.Product;
import com.integrative.wishlistapp.model.Wishlist;
import com.integrative.wishlistapp.model.instance.InstanceBoundary;
import com.integrative.wishlistapp.repository.InstancesRepository;

import java.util.List;
import java.util.Map;

public class WishlistViewModel extends AndroidViewModel {

    private InstancesRepository repository;
    private LiveData<Map<String,Wishlist>> wishlistsLiveData;
    private LiveData<Wishlist> currWishlistLiveData;



    public WishlistViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(InstancesRepository repository) {
        this.repository = repository;
        this.wishlistsLiveData = repository.getWishlists();
        this.currWishlistLiveData = repository.getCurrentWishList();

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


    public void removeProductFromWishlist(int position) {
        this.repository.removeProductFromWishlist(null);
    }
}
