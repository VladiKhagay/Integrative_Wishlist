package com.integrative.wishlistapp.model.user;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

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
                "\"email\":" + '\"' + email + '\"' +
                ", \"domain\":" + '\"' + domain + '\"' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(email, userId.email) && Objects.equals(domain, userId.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, domain);
    }
}
