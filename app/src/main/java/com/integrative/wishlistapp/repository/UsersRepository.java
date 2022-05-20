package com.integrative.wishlistapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.integrative.wishlistapp.apis.UsersService;
import com.integrative.wishlistapp.manager.DataManager;
import com.integrative.wishlistapp.model.user.NewUserBoundary;
import com.integrative.wishlistapp.model.user.UserBoundary;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {

    private static final String TAG = UsersRepository.class.getSimpleName();
    private final UsersService service;
    private final DataManager dataManager;

    private final MutableLiveData<UserBoundary> createdUser;
    private final MutableLiveData<UserBoundary> loggedUser;
    private final MutableLiveData<Boolean> updateResult;
    private final MutableLiveData<List<UserBoundary>> allUsers;
    private final MutableLiveData<Boolean> deleteResult;


    public UsersRepository(UsersService service) {
        this.service = service;
        this.dataManager = DataManager.getInstance();
        createdUser = new MutableLiveData<>();
        loggedUser = new MutableLiveData<>();
        updateResult = new MutableLiveData<>();
        allUsers = new MutableLiveData<>();
        deleteResult = new MutableLiveData<>();

    }


    public void createUser(NewUserBoundary newUserBoundary) {
        service.createUser(newUserBoundary).enqueue(new Callback<UserBoundary>() {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                Log.d(TAG, "createUser onResponse:: " + response);
                if (response.body() != null) {
                    createdUser.postValue(response.body());
                    Log.d(TAG, "createUser result:: "+ response.body());
                }
            }
            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {
                createdUser.postValue(null);
                Log.e(TAG, "createUser onFailure:: " + t.getMessage());
            }
        });

    }

    public void login (String userDomain, String userEmail) {
        service.login(userDomain, userEmail).enqueue(new Callback<UserBoundary>() {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                Log.d(TAG, "login onResponse:: " + response);
                if (response.body() != null) {
                    loggedUser.postValue(response.body());
                    dataManager.setUserBoundary(response.body());
                    Log.d(TAG, "login result:: "+ response.body());
                }
            }

            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {
                loggedUser.postValue(null);
                Log.e(TAG, "login onFailure:: " + t.getMessage());
            }
        });

    }

    public void update(String userDomain, String userEmail) {
        service.update(userDomain, userEmail).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "update onResponse:: " + response);
                if (response.isSuccessful()) {
                    updateResult.postValue(Boolean.TRUE);
                    Log.d(TAG, "update result:: " + response.isSuccessful());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                updateResult.postValue(Boolean.FALSE);
                Log.d(TAG, "update onFailure:: " + t.getMessage());
            }
        });

    }

    public void getAllUsers (String userDomain, String userEmail, int size, int page) {

        service.getAllUsers(userDomain, userEmail, size, page).enqueue(new Callback<List<UserBoundary>>() {
            @Override
            public void onResponse(Call<List<UserBoundary>> call, Response<List<UserBoundary>> response) {
                Log.d(TAG, "getAllUsers onResponse:: " + response);

                if (response.body() != null) {
                    allUsers.postValue(response.body());
                    Log.d(TAG, "getAllUsers result:: " + response.body());
                    Log.d(TAG, "getAllUsers result size:: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<UserBoundary>> call, Throwable t) {
                allUsers.postValue(null);
                Log.e(TAG, "getAllUsers onFailure:: " + t.getMessage());
            }
        });

    }


    public void deleteAll(String userDomain, String userEmail) {

        service.deleteAll(userDomain, userEmail).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "deleteAll onResponse:: " + response);
                if (response.isSuccessful()) {
                    deleteResult.postValue(Boolean.TRUE);
                    Log.d(TAG, "deleteAll result:: " + response.isSuccessful());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                deleteResult.postValue(Boolean.FALSE);
                Log.d(TAG, "deleteAll onFailure:: " + t.getMessage());
            }
        });
    }

}
