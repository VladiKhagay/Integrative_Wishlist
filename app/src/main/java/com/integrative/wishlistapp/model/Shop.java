package com.integrative.wishlistapp.model;

import java.io.Serializable;
import java.util.List;

public class Shop implements Serializable {

    private List<Product> products;

    public Shop() {
    }

    public Shop(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "products: " + products +
                '}';
    }
}
