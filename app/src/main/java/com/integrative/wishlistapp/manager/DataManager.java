package com.integrative.wishlistapp.manager;

import android.util.Log;

import com.integrative.wishlistapp.model.Product;
import com.integrative.wishlistapp.model.Shop;
import com.integrative.wishlistapp.model.User;
import com.integrative.wishlistapp.model.Wishlist;
import com.integrative.wishlistapp.model.user.UserBoundary;
import com.integrative.wishlistapp.model.user.UserId;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static final String TAG = DataManager.class.getSimpleName();
    private static DataManager instance = null;


    private User user;
    private List<Shop> shopList;
    private List<Wishlist>wishlists;
    private Wishlist currentWishlist;

    private UserBoundary userBoundary;
    private DataManager () {
        user = new User();
        userBoundary = new UserBoundary();
        userBoundary.setUserId(new UserId("Asaf@gogo.com", "2022b.timor.bystritskie"));
        userBoundary.setAvatar("ASF");
        userBoundary.setRole("MANAGER");
        userBoundary.setUsername("Sierra");
        shopList = new ArrayList<>();
        wishlists = new ArrayList<>();
        currentWishlist = new Wishlist();

    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    public List<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }

    public Wishlist getCurrentWishlist() {
        return currentWishlist;
    }

    public void setCurrentWishlist(Wishlist currentWishlist) {
        this.currentWishlist = currentWishlist;
    }

    public UserBoundary getUserBoundary() {
        return userBoundary;
    }

    public void setUserBoundary(UserBoundary userBoundary) {
        this.userBoundary = userBoundary;
    }

    public void addProductToWishlist(Product product) {
        if (this.currentWishlist.getProducts().add(product)){
            Log.d(TAG, "addProductToWishlist succeed");
        } else {
            Log.d(TAG, "addProductToWishlist failed");
        }
    }

    public void removeProductFromWishlist(Product product, int index) {
        if (index > 0  && index < currentWishlist.getProducts().size()) {
            if (currentWishlist.getProducts().remove(index) != null) {
                Log.d(TAG, "removeProductFromWishlist succeed");

            } else {
                Log.d(TAG, "removeProductFromWishlist failed");

            }
        }
    }
}
