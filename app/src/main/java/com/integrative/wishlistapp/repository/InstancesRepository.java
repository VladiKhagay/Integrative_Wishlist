package com.integrative.wishlistapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.integrative.wishlistapp.apis.InstancesService;
import com.integrative.wishlistapp.model.Shop;
import com.integrative.wishlistapp.model.Wishlist;
import com.integrative.wishlistapp.model.instance.InstanceBoundary;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstancesRepository {

    private static final String TAG = InstancesRepository.class.getSimpleName();
    private final InstancesService service;

    private MutableLiveData<List<InstanceBoundary>> allInstances;
    private MutableLiveData<InstanceBoundary> retrievedInstance;
    private MutableLiveData<InstanceBoundary> createdInstance;
    private MutableLiveData<InstanceBoundary> updatedInstance;
    private MutableLiveData<Boolean> deleteResult;
    private MutableLiveData<List<InstanceBoundary>> instancesByName;
    private MutableLiveData<List<InstanceBoundary>> instancesByType;
    private MutableLiveData<List<InstanceBoundary>> instancesByLocation;
    private MutableLiveData<List<Wishlist>> wishlists;
    private MutableLiveData<List<Shop>> shops;

    public InstancesRepository(InstancesService service) {
        this.service = service;
        allInstances = new MutableLiveData<>();
        retrievedInstance = new MutableLiveData<>();
        createdInstance = new MutableLiveData<>();
        updatedInstance = new MutableLiveData<>();
        deleteResult = new MutableLiveData<>();
        instancesByName = new MutableLiveData<>();
        instancesByType = new MutableLiveData<>();
        instancesByLocation = new MutableLiveData<>();
        wishlists = new MutableLiveData<>();
        shops = new MutableLiveData<>();
    }

    public void getAllInstances(String userDomain, String userEmail, int size, int page) {

        service.getAllInstances(userDomain, userEmail, size, page).enqueue(new Callback<List<InstanceBoundary>>() {
            @Override
            public void onResponse(Call<List<InstanceBoundary>> call, Response<List<InstanceBoundary>> response) {

                Log.d(TAG, "getAllInstances onResponse:: " + response);

                if (response.body() != null) {
                    allInstances.postValue(response.body());
                    Log.d(TAG, "getAllInstances result:: " + response.body());
                    Log.d(TAG, "getAllInstances result size:: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<InstanceBoundary>> call, Throwable t) {
                allInstances .postValue(null);
                Log.e(TAG, "getAllInstances onFailure:: " + t.getMessage());
            }
        });


    }

    public void retrieveInstance(String instanceDomain, String instanceId, String userDomain, String userEmail) {

        service.retrieveInstance(instanceDomain, instanceId, userDomain, userEmail).enqueue(new Callback<InstanceBoundary>() {
            @Override
            public void onResponse(Call<InstanceBoundary> call, Response<InstanceBoundary> response) {
                Log.d(TAG, "retrieveInstance onResponse:: " + response);
                if (response.body() != null) {
                    retrievedInstance.postValue(response.body());
                    Log.d(TAG, "retrieveInstance result:: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<InstanceBoundary> call, Throwable t) {
                retrievedInstance.postValue(null);
                Log.e(TAG, "retrieveInstance retrieveInstance:: " + t.getMessage());
            }
        });


    }

    public void createInstance(InstanceBoundary instanceBoundary) {
        service.createInstance(instanceBoundary).enqueue(new Callback<InstanceBoundary>() {
            @Override
            public void onResponse(Call<InstanceBoundary> call, Response<InstanceBoundary> response) {
                Log.d(TAG, "createInstance onResponse:: " + response);
                if (response.body() != null) {
                    createdInstance.postValue(response.body());
                    Log.d(TAG, "createInstance result:: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<InstanceBoundary> call, Throwable t) {
                createdInstance.postValue(null);
                Log.e(TAG, "createInstance retrieveInstance:: " + t.getMessage());
            }
        });

    }

    public void updateInstance (InstanceBoundary instanceBoundary) {
        service.updateInstance(instanceBoundary).enqueue(new Callback<InstanceBoundary>() {
            @Override
            public void onResponse(Call<InstanceBoundary> call, Response<InstanceBoundary> response) {
                Log.d(TAG, "updateInstance onResponse:: " + response);
                if (response.body() != null) {
                    updatedInstance.postValue(response.body());
                    Log.d(TAG, "updateInstance result:: " + response.body());
                }
            }
            @Override
            public void onFailure(Call<InstanceBoundary> call, Throwable t) {
                updatedInstance.postValue(null);
                Log.e(TAG, "updateInstance retrieveInstance:: " + t.getMessage());
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
                deleteResult.setValue(Boolean.FALSE);
                Log.d(TAG, "deleteAll onFailure:: " + t.getMessage());
            }
        });


    }

    public void searchInstancesByName(String name, String userDomain, String userEmail, int size, int page) {
        service.searchInstancesByName(name, userDomain, userEmail, size, page).enqueue(new Callback<List<InstanceBoundary>>() {
            @Override
            public void onResponse(Call<List<InstanceBoundary>> call, Response<List<InstanceBoundary>> response) {
                Log.d(TAG, "searchInstancesByName onResponse:: " + response);

                if (response.body() != null) {
                    instancesByName.postValue(response.body());
                    Log.d(TAG, "searchInstancesByName result:: " + response.body());
                    Log.d(TAG, "searchInstancesByName result size:: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<InstanceBoundary>> call, Throwable t) {
                instancesByName.postValue(null);
                Log.e(TAG, "searchInstancesByName onFailure:: " + t.getMessage());
            }
        });


    }


    public void searchInstancesByType(String type, String userDomain, String userEmail, int size, int page) {
        service.searchInstancesByType(type, userDomain, userEmail, size, page).enqueue(new Callback<List<InstanceBoundary>>() {
            @Override
            public void onResponse(Call<List<InstanceBoundary>> call, Response<List<InstanceBoundary>> response) {
                Log.d(TAG, "searchInstancesByType onResponse:: " + response);

                if (response.body() != null) {
                    instancesByType.postValue(response.body());
                    Log.d(TAG, "searchInstancesByType result:: " + response.body());
                    Log.d(TAG, "searchInstancesByType result size:: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<InstanceBoundary>> call, Throwable t) {
                instancesByType.postValue(null);
                Log.e(TAG, "searchInstancesByType onFailure:: " + t.getMessage());
            }
        });


    }

    public void searchInstancesByLocation(String lat, String lng, String distance, String userDomain, String userEmail, int size, int page) {
        service.searchInstancesByLocation(lat, lng, distance, userDomain, userEmail, size, page).enqueue(new Callback<List<InstanceBoundary>>() {
            @Override
            public void onResponse(Call<List<InstanceBoundary>> call, Response<List<InstanceBoundary>> response) {
                Log.d(TAG, "searchInstancesByLocation onResponse:: " + response);

                if (response.body() != null) {
                    instancesByLocation.postValue(response.body());
                    Log.d(TAG, "searchInstancesByLocation result:: " + response.body());
                    Log.d(TAG, "searchInstancesByLocation result size:: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<InstanceBoundary>> call, Throwable t) {
                instancesByLocation.postValue(null);
                Log.e(TAG, "searchInstancesByLocation onFailure:: " + t.getMessage());
            }
        });


    }

    public void retrieveWishlist(String type, String userDomain, String userEmail, int size, int page) {

        service.searchInstancesByType(type, userDomain, userEmail, size, page).enqueue(new Callback<List<InstanceBoundary>>() {
            @Override
            public void onResponse(Call<List<InstanceBoundary>> call, Response<List<InstanceBoundary>> response) {
                Log.d(TAG, "retrieveWishlist onResponse:: " + response);

                if (response.body() != null) {
                    ArrayList<Wishlist> temp = new ArrayList<>();
                    for (InstanceBoundary instanceBoundary : response.body()) {
                        if (instanceBoundary.getCreatedBy().getUserId().getDomain().equals(userDomain)
                                && instanceBoundary.getCreatedBy().getUserId().getEmail().equals(userEmail)) {
                            Gson gson = new Gson();
                            JsonElement jsonElement = gson.toJsonTree(instanceBoundary.getInstanceAttributes());
                            temp.add(gson.fromJson(jsonElement, Wishlist.class));
                        }
                    }
                    wishlists.postValue(temp);

                }
            }

            @Override
            public void onFailure(Call<List<InstanceBoundary>> call, Throwable t) {
                wishlists.postValue(null);
                Log.e(TAG, "retrieveWishlist onFailure:: " + t.getMessage());
            }
        });
    }

    public void retrieveShops(String type, String userDomain, String userEmail, int size, int page) {

        service.searchInstancesByType(type, userDomain, userEmail, size, page).enqueue(new Callback<List<InstanceBoundary>>() {
            @Override
            public void onResponse(Call<List<InstanceBoundary>> call, Response<List<InstanceBoundary>> response) {
                Log.d(TAG, "retrieveShops onResponse:: " + response);

                if (response.body() != null) {
                    ArrayList<Shop> temp = new ArrayList<>();
                    for (InstanceBoundary instanceBoundary : response.body()) {
                        if (instanceBoundary.getCreatedBy().getUserId().getDomain().equals(userDomain)
                                && instanceBoundary.getCreatedBy().getUserId().getEmail().equals(userEmail)) {
                            Gson gson = new Gson();
                            JsonElement jsonElement = gson.toJsonTree(instanceBoundary.getInstanceAttributes());
                            temp.add(gson.fromJson(jsonElement, Shop.class));
                        }
                    }
                    shops.postValue(temp);

                }
            }

            @Override
            public void onFailure(Call<List<InstanceBoundary>> call, Throwable t) {
                shops.postValue(null);
                Log.e(TAG, "retrieveShops onFailure:: " + t.getMessage());
            }
        });
    }

    public MutableLiveData<List<InstanceBoundary>> getAllInstances() {
        return allInstances;
    }

    public void setAllInstances(MutableLiveData<List<InstanceBoundary>> allInstances) {
        this.allInstances = allInstances;
    }

    public MutableLiveData<InstanceBoundary> getRetrievedInstance() {
        return retrievedInstance;
    }

    public void setRetrievedInstance(MutableLiveData<InstanceBoundary> retrievedInstance) {
        this.retrievedInstance = retrievedInstance;
    }

    public MutableLiveData<InstanceBoundary> getCreatedInstance() {
        return createdInstance;
    }

    public void setCreatedInstance(MutableLiveData<InstanceBoundary> createdInstance) {
        this.createdInstance = createdInstance;
    }

    public MutableLiveData<Boolean> getDeleteResult() {
        return deleteResult;
    }

    public void setDeleteResult(MutableLiveData<Boolean> deleteResult) {
        this.deleteResult = deleteResult;
    }

    public MutableLiveData<List<InstanceBoundary>> getInstancesByName() {
        return instancesByName;
    }

    public void setInstancesByName(MutableLiveData<List<InstanceBoundary>> instancesByName) {
        this.instancesByName = instancesByName;
    }

    public MutableLiveData<List<InstanceBoundary>> getInstancesByType() {
        return instancesByType;
    }

    public void setInstancesByType(MutableLiveData<List<InstanceBoundary>> instancesByType) {
        this.instancesByType = instancesByType;
    }

    public MutableLiveData<List<InstanceBoundary>> getInstancesByLocation() {
        return instancesByLocation;
    }

    public void setInstancesByLocation(MutableLiveData<List<InstanceBoundary>> instancesByLocation) {
        this.instancesByLocation = instancesByLocation;
    }

    public MutableLiveData<List<Wishlist>> getWishlists() {
        return wishlists;
    }

    public void setWishlists(MutableLiveData<List<Wishlist>> wishlists) {
        this.wishlists = wishlists;
    }

    public MutableLiveData<InstanceBoundary> getUpdatedInstance() {
        return updatedInstance;
    }

    public void setUpdatedInstance(MutableLiveData<InstanceBoundary> updatedInstance) {
        this.updatedInstance = updatedInstance;
    }

    public MutableLiveData<List<Shop>> getShops() {
        return shops;
    }

    public void setShops(MutableLiveData<List<Shop>> shops) {
        this.shops = shops;
    }
}
