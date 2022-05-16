package com.integrative.wishlistapp.model.user;

import com.google.gson.annotations.SerializedName;

public class UserId {

    @SerializedName("email")
    private String email;
    @SerializedName("domain")
    private String domain;

    public UserId(String email, String domain) {
        this.email = email;
        this.domain = domain;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "UserId{" +
                "email='" + email + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
