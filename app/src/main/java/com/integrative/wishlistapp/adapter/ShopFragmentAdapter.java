package com.integrative.wishlistapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.integrative.wishlistapp.ShopFragment;
import com.integrative.wishlistapp.model.Shop;

import java.util.List;

public class ShopFragmentAdapter extends FragmentStateAdapter{

    private int numOfTabs;
    private List<Shop> shopList;


    public ShopFragmentAdapter(@NonNull FragmentActivity fragmentActivity, int numOfTabs, List<Shop> shopsList) {
        super(fragmentActivity);
        this.numOfTabs = numOfTabs;
        this.shopList = shopsList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ShopFragment.newInstance(shopList.get(position));
    }

    @Override
    public int getItemCount() {
        return numOfTabs;
    }

}
