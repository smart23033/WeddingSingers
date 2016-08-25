package com.weddingsingers.wsapp.main.home;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    TabLayout tabs;
    MainPagerAdapter mAdapter;
    ViewPager pager;

    public MainFragment() {
        // Required empty public constructor
    }

    private static MainFragment instance;

    public static MainFragment getInstance(){
        if(instance == null){
            instance = new MainFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        pager = (ViewPager)view.findViewById(R.id.pager);
        tabs = (TabLayout)view.findViewById(R.id.tabs);

        tabs.addTab(tabs.newTab().setText("TAB1").setTag("tab1"));
        tabs.addTab(tabs.newTab().setText("TAB2").setTag("tab2"));
        tabs.addTab(tabs.newTab().setText("TAB3").setTag("tab3"));

        mAdapter = new MainPagerAdapter(getFragmentManager() ,tabs.getTabCount());

        pager.setAdapter(mAdapter);
        tabs.setupWithViewPager(pager);

        return view;
    }

}
