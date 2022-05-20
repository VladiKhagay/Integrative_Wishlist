package com.integrative.wishlistapp.model.instance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;


public class InstanceBoundary implements Serializable {

    @SerializedName("instanceId")
    private InstanceId instanceId;
    @SerializedName("type")
    private String type;
    @SerializedName("name")
    private String name;
    @SerializedName("active")
    private Boolean active;
    @Expose
    @SerializedName("createdTimestamp")
    private Date createdTimestamp;
    @SerializedName("createdBy")
    private CreatedBy createdBy;
    @SerializedName("location")
    private Location location;
    @SerializedName("instanceAttributes")
    private Map<String, Object> instanceAttributes;


    public InstanceBoundary() {
    }

    public InstanceBoundary(InstanceId instanceId, String type, String name, Boolean active, Date createdTimestamp, CreatedBy createdBy, Location location, Map<String, Object> instanceAttributes) {
        this.instanceId = instanceId;
        this.type = type;
        this.name = name;
        this.active = active;
        this.createdTimestamp = createdTimestamp;
        this.createdBy = createdBy;
        this.location = location;
        this.instanceAttributes = instanceAttributes;
    }

    public InstanceId getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(InstanceId instanceId) {
        this.instanceId = instanceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {

        this.createdTimestamp = createdTimestamp;
    }

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Map<String, Object> getInstanceAttributes() {
        return instanceAttributes;
    }

    public void setInstanceAttributes(Map<String, Object> instanceAttributes) {
        this.instanceAttributes = instanceAttributes;
    }

    @Override
    public String toString() {
        return "InstanceBoundary{" +
                 instanceId +
                ", \"type\":" +'\"' +  type + '\"' +
                ", \"name\":" +'\"' + name + '\"' +
                ", \"active\":" + active +
                ", \"createdTimestamp\":" + createdTimestamp +
                "," + createdBy +
                "," +location +
                ", \"instanceAttributes\":" + instanceAttributes +
                '}';
    }
}
