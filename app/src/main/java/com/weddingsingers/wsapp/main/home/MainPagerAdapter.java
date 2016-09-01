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

    public final static int FRAG_POPULAR = 0;
    public final static int FRAG_NEW = 1;
    public final static int FRAG_EVENT = 2;

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
            case FRAG_POPULAR:
                return VideoListFragment.newInstance("a");
            case FRAG_NEW:
                return VideoListFragment.newInstance("b");
            case FRAG_EVENT:
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
