package com.integrative.wishlistapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.integrative.wishlistapp.apis.InstancesService;
import com.integrative.wishlistapp.apis.UsersService;
import com.integrative.wishlistapp.model.user.NewUserBoundary;
import com.integrative.wishlistapp.model.user.UserBoundary;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {

    private static final String TAG = UsersRepository.class.getSimpleName();
    private final UsersService service;

    public UsersRepository(UsersService service) {
        this.service = service;
    }


    public MutableLiveData<UserBoundary> createUser(NewUserBoundary newUserBoundary) {
        MutableLiveData<UserBoundary> userResponse = new MutableLiveData<>();
        service.createUser(newUserBoundary).enqueue(new Callback<UserBoundary>() {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                Log.d(TAG, "createUser onResponse:: " + response);
                if (response.body() != null) {
                    userResponse.setValue(response.body());
                    Log.d(TAG, "createUser result:: "+ response.body());
                }
            }

            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {
                userResponse.setValue(null);
                Log.e(TAG, "createUser onFailure:: " + t.getMessage());
            }
        });

        return userResponse;
    }

    public MutableLiveData<UserBoundary> login (String userDomain, String userEmail) {
        MutableLiveData<UserBoundary> userResponse = new MutableLiveData<>();

        service.login(userDomain, userEmail).enqueue(new Callback<UserBoundary>() {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                Log.d(TAG, "login onResponse:: " + response);
                if (response.body() != null) {
                    userResponse.setValue(response.body());
                    Log.d(TAG, "login result:: "+ response.body());
                }
            }

            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {
                userResponse.setValue(null);
                Log.e(TAG, "login onFailure:: " + t.getMessage());
            }
        });

        return userResponse;
    }

    public MutableLiveData<Boolean> update(String userDomain, String userEmail) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        service.update(userDomain, userEmail).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "update onResponse:: " + response);
                if (response.isSuccessful()) {
                    result.setValue(Boolean.TRUE);
                    Log.d(TAG, "update result:: " + response.isSuccessful());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                result.setValue(Boolean.FALSE);
                Log.d(TAG, "update onFailure:: " + t.getMessage());
            }
        });

        return result;
    }

    public MutableLiveData<List<UserBoundary>> getAllUsers (String userDomain, String userEmail, int size, int page) {
        MutableLiveData<List<UserBoundary>> responseList = new MutableLiveData<>();

        service.getAllUsers(userDomain, userEmail, size, page).enqueue(new Callback<List<UserBoundary>>() {
            @Override
            public void onResponse(Call<List<UserBoundary>> call, Response<List<UserBoundary>> response) {
                Log.d(TAG, "getAllUsers onResponse:: " + response);

                if (response.body() != null) {
                    responseList.setValue(response.body());
                    Log.d(TAG, "getAllUsers result:: " + response.body());
                    Log.d(TAG, "getAllUsers result size:: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<UserBoundary>> call, Throwable t) {
                responseList.setValue(null);
                Log.e(TAG, "getAllUsers onFailure:: " + t.getMessage());
            }
        });

        return responseList;
    }


    public MutableLiveData<Boolean> deleteAll(String userDomain, String userEmail) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        service.deleteAll(userDomain, userEmail).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "deleteAll onResponse:: " + response);
                if (response.isSuccessful()) {
                    result.setValue(Boolean.TRUE);
                    Log.d(TAG, "deleteAll result:: " + response.isSuccessful());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                result.setValue(Boolean.FALSE);
                Log.d(TAG, "deleteAll onFailure:: " + t.getMessage());
            }
        });

        return result;
    }

}
