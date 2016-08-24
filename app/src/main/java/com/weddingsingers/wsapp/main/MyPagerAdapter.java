package com.weddingsingers.wsapp.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SJSJ on 2016-07-28.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    List<String> items = new ArrayList<>();
    int numOfTabs;

    public void add(String item){
        items.add(item);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    public MyPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance("pager : " + position);
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "tab"+position;
    }
}
