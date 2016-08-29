package com.weddingsingers.wsapp.main.reservationmgm;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.main.home.MainPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationMgmFragment extends Fragment {

    @BindView(R.id.reservation_mgm_tabs)
    TabLayout tabs;

    @BindView(R.id.reservation_mgm_pager)
    ViewPager pager;

    ReservationMgmPagerAdapter mAdapter;

    public ReservationMgmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_reservation_mgm, container, false);

        ButterKnife.bind(this,view);

        tabs.addTab(tabs.newTab().setText("TAB1").setTag("tab1"));
        tabs.addTab(tabs.newTab().setText("TAB2").setTag("tab2"));

        mAdapter = new ReservationMgmPagerAdapter(getChildFragmentManager() ,tabs.getTabCount());

        pager.setAdapter(mAdapter);
        tabs.setupWithViewPager(pager);

        return view;
    }

}
