package com.integrative.wishlistapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Wishlist implements Serializable {

    @SerializedName("products")
    private List<Product> products;
    @SerializedName("description")
    private String description;

    public Wishlist() {
    }

    public Wishlist(List<Product> products, String description) {
        this.products = products;
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "products:" + products +
                ", description:\"" + description + '\"' +
                '}';
    }
}
