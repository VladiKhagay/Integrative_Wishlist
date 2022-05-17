package com.integrative.wishlistapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.integrative.wishlistapp.apis.ActivitiesService;
import com.integrative.wishlistapp.model.activity.ActivityBoundary;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitiesRepository {

    private static final String TAG = ActivitiesRepository.class.getSimpleName();
    private final ActivitiesService service;

    private MutableLiveData<ActivityBoundary> invokedActivity;
    private MutableLiveData<List<ActivityBoundary>> allActivities;
    private MutableLiveData<Boolean> deleteResult;

    public ActivitiesRepository(ActivitiesService activitiesService) {
        this.service = activitiesService;
        invokedActivity = new MutableLiveData<>();
        allActivities = new MutableLiveData<>();
        deleteResult = new MutableLiveData<>();
    }

    public void invoke(ActivityBoundary activityBoundary) {
        service.invoke(activityBoundary).enqueue(new Callback<ActivityBoundary>() {
            @Override
            public void onResponse(Call<ActivityBoundary> call, Response<ActivityBoundary> response) {
                Log.d(TAG, "invoke onResponse:: " + response);

                if (response.body() != null) {
                    invokedActivity.postValue(response.body());
                    Log.d(TAG, "invoke activity result:: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<ActivityBoundary> call, Throwable t) {
                invokedActivity.postValue(null);
                Log.e(TAG, " invoke onFailure:: " + t.getMessage());
            }
        });
    }

    public void getAllActivities(String userDomain, String userEmail, int size, int page) {
        service.getAllActivities(userDomain, userEmail, size, page).enqueue(new Callback<List<ActivityBoundary>>() {
            @Override
            public void onResponse(Call<List<ActivityBoundary>> call, Response<List<ActivityBoundary>> response) {
                Log.d(TAG, "getAllActivities onResponse:: " + response);

                if (response.body() != null) {
                    allActivities.postValue(response.body());
                    Log.d(TAG, "getAllActivities result:: " + response.body());
                    Log.d(TAG, "getAllActivities result size:: " + response.body().size());

                }
            }

            @Override
            public void onFailure(Call<List<ActivityBoundary>> call, Throwable t) {
                allActivities.postValue(null);
                Log.e(TAG, " getAllActivities onFailure:: " + t.getMessage());
            }
        });
    }

    public void deleteAllActivities(String userDomain, String userEmail) {
        service.deleteAllActivities(userDomain, userEmail).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                Log.d(TAG, "deleteAllActivities onResponse:: " + response);
                if (response.isSuccessful()) {
                    deleteResult.postValue(Boolean.TRUE);
                    Log.d(TAG, "deleteAllActivities result:: " + response.isSuccessful());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                deleteResult.postValue(Boolean.FALSE);
                Log.d(TAG, "deleteAllActivities onFailure:: " + t.getMessage());
            }
        });
    }

    public MutableLiveData<ActivityBoundary> getInvokedActivity() {
        return invokedActivity;
    }

    public MutableLiveData<List<ActivityBoundary>> getAllActivities() {
        return allActivities;
    }

    public MutableLiveData<Boolean> getDeleteResult() {
        return deleteResult;
    }
}
