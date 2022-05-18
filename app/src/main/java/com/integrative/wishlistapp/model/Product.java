package com.integrative.wishlistapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private Double price;
    @SerializedName("category")
    private String category;

    public Product() {
    }

    public Product(String name, Double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name:\"" + name + '\"' +
                ", price:" + price +
                ", category:\"" + category + '\"' +
                '}';
    }
}
