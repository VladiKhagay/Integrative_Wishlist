package com.integrative.wishlistapp.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private String price;
    @SerializedName("brand")
    private String brand;
    @SerializedName("category")
    private String category;
    @SerializedName("subCategory")
    private String subCategory;

    public Product() {
    }

    public Product(String name, String price, String brand, String category, String subCategory) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                '}';
    }
}
