package com.weddingsingers.wsapp.main.mypage;

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
public class MyPageSingerPagerAdapter extends FragmentPagerAdapter {
    List<String> items = new ArrayList<>();
    int numOfTabs;

    public final static int MYPAGE_FRAGMENT = 0;
    public final static int PROFILE_FRAGMENT = 1;

    Context context = MyApplication.getContext();

    String tabTitles[] = new String[] {
            context.getString(R.string.my_page_singer_tab_mypage),
            context.getString(R.string.my_page_singer_tab_profile),
    };

    public void add(String item){
        items.add(item);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    public MyPageSingerPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case MYPAGE_FRAGMENT:
                return SingerMyPageFragment.newInstance("a");
            case PROFILE_FRAGMENT:
                return SingerProfileFragment.newInstance("b");
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
