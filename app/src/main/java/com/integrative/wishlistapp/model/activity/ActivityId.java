package com.integrative.wishlistapp.model.activity;

import com.google.gson.annotations.SerializedName;

public class ActivityId {

    @SerializedName("domain")
    private String domain;
    @SerializedName("id")
    private String id;

    public ActivityId() {
    }

    public ActivityId(String domain, String id) {
        this.domain = domain;
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ActivityId{" +
                "\"domain\":"+ '\"' + domain + '\"' +
                ", \"id:\"" + '\"'+ id + '\"' +
                '}';
    }
}
