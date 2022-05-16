package com.integrative.wishlistapp.model;

import com.google.gson.annotations.SerializedName;

public class Wishlist {

    @SerializedName("products")
    private Product products[];
    @SerializedName("totalPrice")
    private double totalPrice;
    @SerializedName("maximumBudget")
    private double maximumBudget;
    @SerializedName("description")
    private String description;

}
