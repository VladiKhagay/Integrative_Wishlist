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

    public UserBoundary() {
    }

    public UserBoundary(UserId userId, String username, String role, String avatar) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.avatar = avatar;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserBoundary{" +
                userId +
                ", \"username\":" +'\"'+ username + '\"' +
                ", \"role\":" + '\"' + role + '\"' +
                ", \"avatar\": " + '\"' + avatar + '\"' +
                '}';
    }
}
