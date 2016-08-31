package com.weddingsingers.wsapp.main.reservationmgm;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.payment.payment.PaymentActivity;
import com.weddingsingers.wsapp.function.payment.payment.PaymentFragment;
import com.weddingsingers.wsapp.main.home.MainPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationMgmFragment extends Fragment {

    public static final int FRAG_INFO = 0;

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
        View view = inflater.inflate(R.layout.fragment_reservation_mgm, container, false);

        ButterKnife.bind(this, view);

        tabs.addTab(tabs.newTab().setText("TAB1").setTag("tab1"));
        tabs.addTab(tabs.newTab().setText("TAB2").setTag("tab2"));

        mAdapter = new ReservationMgmPagerAdapter(getChildFragmentManager(), tabs.getTabCount());

        pager.setAdapter(mAdapter);
        tabs.setupWithViewPager(pager);

        return view;
    }

    public void startPaymentActivity() {
        Intent intent = new Intent(getContext(), PaymentActivity.class);
        intent.putExtra("fragmentName", "ReservedOneFragment");
        startActivityForResult(intent, FRAG_INFO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == FRAG_INFO){
            if(resultCode == Activity.RESULT_OK){
                String fragmentName = data.getStringExtra(PaymentActivity.RESULT_MESSAGE);
                Log.i("ReservationMgmFragment","FragmentName 받았다! : " + fragmentName);
               pager.setCurrentItem(1);
            }else{
                Log.i("ReservationMgmFragment","FragmentName 못받았다");
            }
        }
    }
}
