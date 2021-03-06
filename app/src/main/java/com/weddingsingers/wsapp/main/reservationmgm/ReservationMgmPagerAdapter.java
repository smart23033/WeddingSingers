package com.weddingsingers.wsapp.main.reservationmgm;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class ReservationMgmPagerAdapter extends FragmentPagerAdapter {
    List<String> items = new ArrayList<>();
    int numOfTabs;

    public final static int RESERVATION_LIST = 0;
    public final static int RESERVED_ONE = 1;
//    public final static int RESERVED_CUSTOMER = 2;

    Context context = MyApplication.getContext();

    String tabTitles[] = new String[] {
            context.getString(R.string.reservation_list),
            context.getString(R.string.reserved_one),
    };

    public void add(String item){
        items.add(item);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    public ReservationMgmPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case RESERVATION_LIST:
                return new ReservationListFragment();
            case RESERVED_ONE:
                return new ReservedOneFragment();
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
