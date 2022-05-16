package com.integrative.wishlistapp.apis;

import androidx.annotation.Nullable;

import com.integrative.wishlistapp.model.activity.ActivityBoundary;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ActivitiesService {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/iob/activities")
    @Nullable
    Call<ActivityBoundary> invoke(@Body @NotNull ActivityBoundary activityBoundary);

    @Headers({"Accept: application/json"})
    @GET("/iob/admin/activities")
    @Nullable
    Call<List<ActivityBoundary>> getAllActivities(
            @Query("userDomain") @NotNull String userDomain
            , @Query("userEmail") @NotNull String userEmail
            , @Query("size") int size
            , @Query("page") int page);

    @DELETE("/iob/admin/activities")
    @Nullable
    Call<Void> deleteAllActivities(
            @Query("userDomain") @NotNull String userDomain
            , @Query("userEmail") @NotNull String userEmail
            );

}
