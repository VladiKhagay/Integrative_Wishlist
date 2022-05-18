package com.integrative.wishlistapp.model;

import com.google.gson.annotations.SerializedName;
import com.integrative.wishlistapp.model.user.UserId;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("userId")
    private String userId;
    @SerializedName("username")
    private String username;
    @SerializedName("role")
    private String role;
    @SerializedName("avatar")
    private String avatar;

    public User() {
    }

    public User(String userId, String username, String role, String avatar) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.avatar = avatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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
        return "User{" +
                "userId:\"" + userId + '\"' +
                ", username:\"" + username + '\"' +
                ", role:\"" + role + '\"' +
                ", avatar:\"" + avatar + '\"' +
                '}';
    }
}
