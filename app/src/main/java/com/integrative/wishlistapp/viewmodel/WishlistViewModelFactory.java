package com.integrative.wishlistapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.integrative.wishlistapp.repository.InstancesRepository;

import java.lang.reflect.InvocationTargetException;

public class WishlistViewModelFactory implements ViewModelProvider.Factory{

    private static final String TAG = WishlistViewModelFactory.class.getSimpleName();
    private final InstancesRepository repository;
    private final Application application;

    public WishlistViewModelFactory(InstancesRepository repository, Application application) {
        this.repository = repository;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        try {
            return modelClass.getConstructor(WishlistViewModel.class).newInstance(repository,application);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException("ViewModel not found");
        }

    }
}
