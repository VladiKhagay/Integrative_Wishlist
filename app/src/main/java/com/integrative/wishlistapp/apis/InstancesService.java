package com.integrative.wishlistapp.apis;

import com.integrative.wishlistapp.model.instance.InstanceBoundary;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InstancesService {

    @Headers({"Accept: application/json"})
    @GET("/iob/instances")
    @Nullable
    Call<List<InstanceBoundary>> getAllInstances(@Query("userDomain") @NotNull String userDomain
            , @Query("userEmail") @NotNull String userEmail
            , @Query("size") int size
            , @Query("page") int page
    );

    @Headers({"Accept: application/json"})
    @GET("/iob/instances/{instanceDomain}/{instanceId}")
    @Nullable
    Call<InstanceBoundary> retrieveInstance(@Path("instanceDomain") @NotNull String instanceDomain
            , @Path("instanceId") @NotNull String instanceId
            , @Query("userDomain") @NotNull String userDomain
            , @Query("userEmail") @NotNull String userEmail
    );

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/iob/instances")
    @Nullable
    Call<InstanceBoundary> createInstance(@Body @NotNull InstanceBoundary instanceBoundary);

    @Headers({"Content-Type: application/json"})
    @PUT("/iob/instances/{instanceDomain}/{instanceId}")
    @Nullable
    Call<Void> updateInstance(@Path("instanceDomain") @NotNull String instanceDomain
            , @Path("instanceId") @NotNull String instanceId
            , @Query("userDomain") @NotNull String userDomain
            , @Query("userEmail") @NotNull String userEmail
    );

    @DELETE("/iob/admin/instances")
    @Nullable
    Call<Void> deleteAll(@Query("userDomain") @NotNull String userDomain
            , @Query("userEmail") @NotNull String userEmail);

    @Headers({"Accept: application/json"})
    @GET
    @Nullable
    Call<List<InstanceBoundary>> searchInstancesByName(@Path("name") @NotNull String name
            , @Query("userDomain") @NotNull String userDomain
            , @Query("userEmail") @NotNull String userEmail
            , @Query("size") int size
            , @Query("page") int page
    );

    @Headers({"Accept: application/json"})
    @GET
    @Nullable
    Call<List<InstanceBoundary>> searchInstancesByType(@Path("type") String type
            , @Query("userDomain") @NotNull String userDomain
            , @Query("userEmail") @NotNull String userEmail
            , @Query("size") int size
            , @Query("page") int page
    );

    @Headers({"Accept: application/json"})
    @GET
    @Nullable
    Call<List<InstanceBoundary>> searchInstancesByLocation(@Path("lat") @NotNull String lat
            , @Path("lng") @NotNull String lng
            , @Path("distance") @NotNull String distance
            , @Query("userDomain") @NotNull String userDomain
            , @Query("userEmail") @NotNull String userEmail
            , @Query("size") int size
            , @Query("page") int page
    );
}
