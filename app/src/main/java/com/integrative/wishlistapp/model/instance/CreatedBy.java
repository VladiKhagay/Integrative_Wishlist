package com.integrative.wishlistapp.model.instance;

import com.google.gson.annotations.SerializedName;
import com.integrative.wishlistapp.model.user.UserId;

public class CreatedBy {

    @SerializedName("userId")
    private UserId userId;

    public CreatedBy() {
    }

    public CreatedBy(UserId userId) {
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
        return "CreatedBy{" +
                "\"userId\":" + '\"' + userId + '\"' +
                '}';
    }
}
