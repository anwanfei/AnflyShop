package com.anfly.anflyshop.ui.sort;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class VpCatalogDetailsAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;

    public VpCatalogDetailsAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
