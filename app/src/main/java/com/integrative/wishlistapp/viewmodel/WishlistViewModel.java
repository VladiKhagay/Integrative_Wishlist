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


    private static final String TAG = WishlistViewModel.class.getSimpleName();
    private final Application application;
    private final InstancesRepository repository;

    private LiveData<List<InstanceBoundary>> instances;

    public WishlistViewModel(@NonNull InstancesRepository repository, @NonNull Application application) {
        super(application);

        this.application = application;
        this.repository = repository;
    }

}
