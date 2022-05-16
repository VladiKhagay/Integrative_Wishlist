package com.integrative.wishlistapp.apis;

import com.integrative.wishlistapp.model.user.NewUserBoundary;
import com.integrative.wishlistapp.model.user.UserBoundary;

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

public interface UsersService {


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/iob/users/")
    @Nullable
    Call<UserBoundary> createUser(@Body NewUserBoundary newUserBoundary);


    @Headers({"Accept: application/json"})
    @GET("/iob/users/login/{userDomain}/{userEmail}")
    @Nullable
    Call<UserBoundary> login(@Path("userDomain") @NotNull String userDomain
            , @Path("userEmail") @NotNull String userEmail);

    @Headers({"Content-Type: application/json"})
    @PUT("/iob/users/{userDomain}/{userEmail}")
    @Nullable
    Call<Void> update(@Path("userDomain") @NotNull String userDomain
            , @Path("userEmail") @NotNull String userEmail);

    @GET("/iob/admin/users")
    @Nullable
    Call<List<UserBoundary>> getAllUsers(@Query("userDomain") @NotNull String userDomain
            , @Query("userEmail") @NotNull String userEmail
            , @Query("size") int size
            , @Query("page") int page);

    @DELETE("/iob/admin/users")
    @Nullable
    Call<Void> deleteAll(@Query("userDomain") @NotNull String userDomain
            , @Query("userEmail") @NotNull String userEmail);
}
