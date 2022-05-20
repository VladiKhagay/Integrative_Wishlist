package com.integrative.wishlistapp.model.activity;

import com.google.gson.annotations.SerializedName;
import com.integrative.wishlistapp.model.instance.InstanceId;

public class Instance {

    @SerializedName("instanceId")
    private InstanceId instanceId;

    public Instance() {
    }

    public Instance(InstanceId instanceId) {
        this.instanceId = instanceId;
    }

    public InstanceId getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(InstanceId instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public String toString() {
        return "Instance{" +
                 instanceId +
                '}';
    }
}
