package com.weddingsingers.wsapp.main.mypage;


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
public class MyPageSingerFragment extends Fragment {

    TabLayout tabs;
    MyPageSingerPagerAdapter mAdapter;
    ViewPager pager;

    public MyPageSingerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_page_singer, container, false);

        pager = (ViewPager) view.findViewById(R.id.my_page_singer_pager);
        tabs = (TabLayout) view.findViewById(R.id.my_page_singer_tabs);

        tabs.addTab(tabs.newTab().setText("TAB1").setTag("tab1"));
        tabs.addTab(tabs.newTab().setText("TAB2").setTag("tab2"));

        mAdapter = new MyPageSingerPagerAdapter(getChildFragmentManager(), tabs.getTabCount());

        pager.setAdapter(mAdapter);
        tabs.setupWithViewPager(pager);

        return view;
    }

}