package com.weddingsingers.wsapp.main.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SJSJ on 2016-07-28.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    List<String> items = new ArrayList<>();
    int numOfTabs;

    public final static int POPULAR_FRAGMENT = 0;
    public final static int NEW_FRAGMENT = 1;
    public final static int EVENT_FRAGMENT = 2;

    Context context = MyApplication.getContext();

    String tabTitles[] = new String[] {
            context.getString(R.string.main_tab_popular),
            context.getString(R.string.main_tab_new),
            context.getString(R.string.main_tab_event)
    };

    public void add(String item){
        items.add(item);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    public MainPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case POPULAR_FRAGMENT:
                return VideoListFragment.newInstance("a");
            case NEW_FRAGMENT:
                return VideoListFragment.newInstance("b");
            case EVENT_FRAGMENT:
                return EventListFragment.newInstance("c");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
