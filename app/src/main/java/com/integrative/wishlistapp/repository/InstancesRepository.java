package com.integrative.wishlistapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.integrative.wishlistapp.apis.InstancesService;
import com.integrative.wishlistapp.model.activity.Instance;
import com.integrative.wishlistapp.model.instance.InstanceBoundary;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstancesRepository {

    private static final String TAG = InstancesRepository.class.getSimpleName();
    private final InstancesService service;

    public InstancesRepository(InstancesService service) {
        this.service = service;
    }

    public MutableLiveData<List<InstanceBoundary>> getAllInstances(String userDomain, String userEmail, int size, int page) {
        MutableLiveData<List<InstanceBoundary>> responseList = new MutableLiveData<>();

        service.getAllInstances(userDomain, userEmail, size, page).enqueue(new Callback<List<InstanceBoundary>>() {
            @Override
            public void onResponse(Call<List<InstanceBoundary>> call, Response<List<InstanceBoundary>> response) {

                Log.d(TAG, "getAllInstances onResponse:: " + response);

                if (response.body() != null) {
                    responseList.setValue(response.body());
                    Log.d(TAG, "getAllInstances result:: " + response.body());
                    Log.d(TAG, "getAllInstances result size:: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<InstanceBoundary>> call, Throwable t) {
                responseList.setValue(null);
                Log.e(TAG, "getAllInstances onFailure:: " + t.getMessage());
            }
        });

        return responseList;
    }

    public MutableLiveData<InstanceBoundary> retrieveInstance(String instanceDomain, String instanceId, String userDomain, String userEmail) {
        MutableLiveData<InstanceBoundary> responseInstance = new MutableLiveData<>();
        service.retrieveInstance(instanceDomain, instanceId, userDomain, userEmail).enqueue(new Callback<InstanceBoundary>() {
            @Override
            public void onResponse(Call<InstanceBoundary> call, Response<InstanceBoundary> response) {
                Log.d(TAG, "retrieveInstance onResponse:: " + response);
                if (response.body() != null) {
                    responseInstance.setValue(response.body());
                    Log.d(TAG, "retrieveInstance result:: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<InstanceBoundary> call, Throwable t) {
                responseInstance.setValue(null);
                Log.e(TAG, "retrieveInstance retrieveInstance:: " + t.getMessage());
            }
        });

        return responseInstance;
    }

    public MutableLiveData<InstanceBoundary> createInstance(InstanceBoundary instanceBoundary) {
        MutableLiveData<InstanceBoundary> responseInstance = new MutableLiveData<>();

        service.createInstance(instanceBoundary).enqueue(new Callback<InstanceBoundary>() {
            @Override
            public void onResponse(Call<InstanceBoundary> call, Response<InstanceBoundary> response) {
                Log.d(TAG, "createInstance onResponse:: " + response);
                if (response.body() != null) {
                    responseInstance.setValue(response.body());
                    Log.d(TAG, "createInstance result:: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<InstanceBoundary> call, Throwable t) {
                responseInstance.setValue(null);
                Log.e(TAG, "createInstance retrieveInstance:: " + t.getMessage());
            }
        });
        return responseInstance;
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

    public MutableLiveData<List<InstanceBoundary>> searchInstancesByName(String name, String userDomain, String userEmail, int size, int page) {
        MutableLiveData<List<InstanceBoundary>> responseList = new MutableLiveData<>();
        service.searchInstancesByName(name, userDomain, userEmail, size, page).enqueue(new Callback<List<InstanceBoundary>>() {
            @Override
            public void onResponse(Call<List<InstanceBoundary>> call, Response<List<InstanceBoundary>> response) {
                Log.d(TAG, "searchInstancesByName onResponse:: " + response);

                if (response.body() != null) {
                    responseList.setValue(response.body());
                    Log.d(TAG, "searchInstancesByName result:: " + response.body());
                    Log.d(TAG, "searchInstancesByName result size:: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<InstanceBoundary>> call, Throwable t) {
                responseList.setValue(null);
                Log.e(TAG, "searchInstancesByName onFailure:: " + t.getMessage());
            }
        });

        return responseList;
    }


    public MutableLiveData<List<InstanceBoundary>> searchInstancesByType(String type, String userDomain, String userEmail, int size, int page) {
        MutableLiveData<List<InstanceBoundary>> responseList = new MutableLiveData<>();
        service.searchInstancesByType(type, userDomain, userEmail, size, page).enqueue(new Callback<List<InstanceBoundary>>() {
            @Override
            public void onResponse(Call<List<InstanceBoundary>> call, Response<List<InstanceBoundary>> response) {
                Log.d(TAG, "searchInstancesByType onResponse:: " + response);

                if (response.body() != null) {
                    responseList.setValue(response.body());
                    Log.d(TAG, "searchInstancesByType result:: " + response.body());
                    Log.d(TAG, "searchInstancesByType result size:: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<InstanceBoundary>> call, Throwable t) {
                responseList.setValue(null);
                Log.e(TAG, "searchInstancesByType onFailure:: " + t.getMessage());
            }
        });

        return responseList;
    }

    public MutableLiveData<List<InstanceBoundary>> searchInstancesByLocation(String lat, String lng, String distance, String userDomain, String userEmail, int size, int page) {
        MutableLiveData<List<InstanceBoundary>> responseList = new MutableLiveData<>();
        service.searchInstancesByLocation(lat, lng, distance, userDomain, userEmail, size, page).enqueue(new Callback<List<InstanceBoundary>>() {
            @Override
            public void onResponse(Call<List<InstanceBoundary>> call, Response<List<InstanceBoundary>> response) {
                Log.d(TAG, "searchInstancesByLocation onResponse:: " + response);

                if (response.body() != null) {
                    responseList.setValue(response.body());
                    Log.d(TAG, "searchInstancesByLocation result:: " + response.body());
                    Log.d(TAG, "searchInstancesByLocation result size:: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<InstanceBoundary>> call, Throwable t) {
                responseList.setValue(null);
                Log.e(TAG, "searchInstancesByLocation onFailure:: " + t.getMessage());
            }
        });

        return responseList;
    }
}
