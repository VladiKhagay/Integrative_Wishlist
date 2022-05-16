package com.integrative.wishlistapp.model.user;

import com.google.gson.annotations.SerializedName;

public class UserBoundary {

    @SerializedName("userId")
    private UserId userId;
    @SerializedName("username")
    private String username;
    @SerializedName("role")
    private String role;
    @SerializedName("avatar")
    private String avatar;

}
