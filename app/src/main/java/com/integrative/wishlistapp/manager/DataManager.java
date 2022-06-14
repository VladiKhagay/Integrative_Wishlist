package com.integrative.wishlistapp.manager;

import com.integrative.wishlistapp.AppConstants;
import com.integrative.wishlistapp.model.Product;
import com.integrative.wishlistapp.model.Shop;
import com.integrative.wishlistapp.model.User;
import com.integrative.wishlistapp.model.Wishlist;
import com.integrative.wishlistapp.model.activity.ActivityBoundary;
import com.integrative.wishlistapp.model.instance.InstanceBoundary;
import com.integrative.wishlistapp.model.user.UserBoundary;
import com.integrative.wishlistapp.model.user.UserId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {

    private static final String TAG = DataManager.class.getSimpleName();
    private static DataManager instance = null;


    private User user;
    private Map<String , Shop> shopsMap;
    private Map<String, Wishlist> wishlistMap;
    private Wishlist currentWishlist;
    private InstanceBoundary activeInstance;

    private UserBoundary userBoundary;
    private List<InstanceBoundary> instanceBoundaries;
    private List<ActivityBoundary> activityBoundaries;
    private String managerEmail = "Dima@gogo.com";

    private DataManager () {
        user = new User();
        shopsMap = new HashMap<>();
        wishlistMap = new HashMap<>();
        currentWishlist = new Wishlist();
        activeInstance = new InstanceBoundary();
        userBoundary = new UserBoundary();

//        /* Dummy User */
//        userBoundary.setUsername("Sierra");
//        userBoundary.setUserId(new UserId("Asaf@gogo.com","2022b.timor.bystritskie"));
//        userBoundary.setAvatar("ASF");


        instanceBoundaries = new ArrayList<>();
        activityBoundaries = new ArrayList<>();
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

    public Map<String, Shop> getShopsMap() {
        return shopsMap;
    }

    public void setShopsMap(Map<String, Shop> shopsMap) {
        this.shopsMap = shopsMap;
    }

    public Map<String, Wishlist> getWishlistMap() {
        return wishlistMap;
    }

    public void setWishlistMap(Map<String, Wishlist> wishlistMap) {
        this.wishlistMap = wishlistMap;
    }

    public Wishlist getCurrentWishlist() {
        return currentWishlist;
    }

    public void setCurrentWishlist(Wishlist currentWishlist) {
        this.currentWishlist = currentWishlist;
    }

    public InstanceBoundary getActiveInstance() {
        return activeInstance;
    }

    public void setActiveInstance(InstanceBoundary activeInstance) {
        this.activeInstance = activeInstance;
    }

    public UserBoundary getUserBoundary() {
        return userBoundary;
    }

    public void setUserBoundary(UserBoundary userBoundary) {
        this.userBoundary = userBoundary;
    }

    public List<InstanceBoundary> getInstanceBoundaries() {
        return instanceBoundaries;
    }

    public void setInstanceBoundaries(List<InstanceBoundary> instanceBoundaries) {
        this.instanceBoundaries = instanceBoundaries;
    }

    public List<ActivityBoundary> getActivityBoundaries() {
        return activityBoundaries;
    }

    public void setActivityBoundaries(List<ActivityBoundary> activityBoundaries) {
        this.activityBoundaries = activityBoundaries;
    }

    public void setWishlistsMap(List<Wishlist> wishlists) {
        if (!this.wishlistMap.isEmpty()) {
            this.wishlistMap.clear();
        }
        if (wishlists != null) {
            for (int i = 0; i < wishlists.size(); i ++) {
                this.wishlistMap.put(String.valueOf(i), wishlists.get(i));
            }
        }
    }

    public void setShopsMap(List<Shop> shops) {
        if(!this.shopsMap.isEmpty()) {
            this.shopsMap.clear();
        }

        if (shops != null) {
            for(int i = 0 ; i < shops.size(); i++) {
                this.shopsMap.put(shops.get(i).getProducts().get(0).getCategory(), shops.get(i));
            }
        }
    }

    public void addProductToWishlist(Product product) {
        if (product != null) {
            this.currentWishlist.getProducts().add(product);
        }
    }

    public void removeProductFromWishlist(Product product) {
        if(product != null) {

            int index = -1;
            for (int i = 0; i < this.currentWishlist.getProducts().size(); i ++) {
                if (this.currentWishlist.getProducts().get(i).getName().equals(product.getName())) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                this.currentWishlist.getProducts().remove(product);
            }
        }
    }

    public void addWishlist(Wishlist wishlist) {
        if(wishlist != null) {
            this.wishlistMap.put(String.valueOf(this.wishlistMap.size()), wishlist);
        }

    }

    public void addShop(Shop shop) {

        if (shop != null) {
            this.shopsMap.put(shop.getProducts().get(0).getCategory(), shop);
        }
    }

    public String getManagerEmail() {
        return this.managerEmail;
    }
}
