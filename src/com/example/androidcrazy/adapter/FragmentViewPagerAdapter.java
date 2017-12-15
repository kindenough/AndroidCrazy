
package com.example.androidcrazy.adapter;

import java.util.HashMap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {

    private HashMap<Integer, Fragment> fragmentsList;

    public FragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentViewPagerAdapter(FragmentManager fm,
            HashMap<Integer, Fragment> fragments) {
        super(fm);
        this.fragmentsList = fragments;
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public Fragment getItem(int index) {
        return fragmentsList.get(index);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

}
