package com.integrative.wishlistapp.model;

import java.io.Serializable;
import java.util.List;

public class Shop implements Serializable {

    private List<Product> products;
    private String name;

    public Shop() {
    }

    public Shop(List<Product> products, String name) {
        this.products = products;
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "products: " + products +
                ", \"name\":" + '\"' + name + '\"' +
                '}';
    }
}
