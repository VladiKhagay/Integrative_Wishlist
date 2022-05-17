package com.integrative.wishlistapp.model.instance;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
    @SerializedName("createdTimestamp")
    private String createdTimestamp;
    @SerializedName("createdBy")
    private CreatedBy createdBy;
    @SerializedName("location")
    private Location location;
    @SerializedName("instanceAttributes")
    private Map<String, Object> instanceAttributes;



    public InstanceBoundary() {
    }

    public InstanceBoundary(InstanceId instanceId, String type, String name, Boolean active, String createdTimestamp, CreatedBy createdBy, Location location, Map<String, Object> instanceAttributes) {
        this.instanceId = instanceId;
        this.type = type;
        this.name = name;
        this.active = active;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        this.createdTimestamp = df.format(createdTimestamp);
//        this.createdTimestamp = createdTimestamp;
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

    public String getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(String createdTimestamp) {

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
                "instanceId=" + instanceId +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", createdTimestamp=" + createdTimestamp +
                ", createdBy=" + createdBy +
                ", location=" + location +
                ", instanceAttributes=" + instanceAttributes +
                '}';
    }
}
