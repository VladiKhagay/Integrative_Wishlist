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

    public ActivitiesRepository(ActivitiesService activitiesService) {
        this.service = activitiesService;
    }

    public LiveData<ActivityBoundary> invoke(ActivityBoundary activityBoundary) {

        final MutableLiveData<ActivityBoundary> responseActivity = new MutableLiveData<>();

        service.invoke(activityBoundary).enqueue(new Callback<ActivityBoundary>() {
            @Override
            public void onResponse(Call<ActivityBoundary> call, Response<ActivityBoundary> response) {
                Log.d(TAG, "invoke onResponse:: " + response);

                if (response.body() != null) {
                    responseActivity.setValue(response.body());

                    Log.d(TAG, "invoke activity result:: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<ActivityBoundary> call, Throwable t) {
                responseActivity.setValue(null);
                Log.e(TAG, " invoke onFailure:: " + t.getMessage());
            }
        });
        return responseActivity;

    }

    public LiveData<List<ActivityBoundary>> getAllActivities(String userDomain, String userEmail, int size, int page) {
        final MutableLiveData<List<ActivityBoundary>> responseList = new MutableLiveData<>();

        service.getAllActivities(userDomain, userEmail, size, page).enqueue(new Callback<List<ActivityBoundary>>() {
            @Override
            public void onResponse(Call<List<ActivityBoundary>> call, Response<List<ActivityBoundary>> response) {
                Log.d(TAG, "getAllActivities onResponse:: " + response);

                if (response.body() != null) {
                    responseList.setValue(response.body());
                    Log.d(TAG, "getAllActivities result:: " + response.body());
                    Log.d(TAG, "getAllActivities result size:: " + response.body().size());

                }
            }

            @Override
            public void onFailure(Call<List<ActivityBoundary>> call, Throwable t) {
                responseList.setValue(null);
                Log.e(TAG, " getAllActivities onFailure:: " + t.getMessage());
            }
        });

        return responseList;
    }

    public LiveData<Boolean> deleteAllActivities(String userDomain, String userEmail) {
        final MutableLiveData<Boolean> result = new MutableLiveData<>();

        service.deleteAllActivities(userDomain, userEmail).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                Log.d(TAG, "deleteAllActivities onResponse:: " + response);
                if (response.isSuccessful()) {
                    result.setValue(Boolean.TRUE);
                    Log.d(TAG, "deleteAllActivities result:: " + response.isSuccessful());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                result.setValue(Boolean.FALSE);
                Log.d(TAG, "deleteAllActivities onFailure:: " + t.getMessage());
            }
        });
        return result;
    }
}
