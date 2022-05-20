package com.integrative.wishlistapp.model.instance;

import com.google.gson.annotations.SerializedName;

public class InstanceId {

    @SerializedName("domain")
    private String domain;
    @SerializedName("id")
    private String id;

    public InstanceId() {
    }

    public InstanceId(String domain, String id) {
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
        return "InstanceId{" +
                "\"domain\":" + '\"' + domain + '\"' +
                ", \"id\":" + '\"'+ id + '\"' +
                '}';
    }
}
