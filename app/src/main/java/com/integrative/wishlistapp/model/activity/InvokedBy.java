package com.integrative.wishlistapp.model.activity;

import com.google.gson.annotations.SerializedName;
import com.integrative.wishlistapp.model.user.UserId;

public class InvokedBy {

    @SerializedName("userId")
    private UserId userId;



    public InvokedBy(UserId userId) {
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "InvokedBy{" +
                "userId=" + userId +
                '}';
    }
}
