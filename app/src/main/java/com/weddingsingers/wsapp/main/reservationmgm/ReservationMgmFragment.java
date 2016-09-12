package com.weddingsingers.wsapp.main.reservationmgm;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.payment.payment.PaymentActivity;
import com.weddingsingers.wsapp.function.reservation.cancelreservation.CancelReservationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationMgmFragment extends Fragment {

    public static final int FRAG_RESERVATION_MGM = 320;
    public static final int FRAG_RESERVATION_LIST = 321;
    public static final int FRAG_RESERVED_ONE = 322;
    public static final int TAB_RESERVATION_LIST = 0;
    public static final int TAB_RESERVED_ONE = 1;

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

    public void startPaymentActivity(int estimateId) {
        Intent intent = new Intent(getContext(), PaymentActivity.class);
        intent.putExtra(PaymentActivity.FRAG_NAME, "ReservedOneFragment");
        intent.putExtra(PaymentActivity.EXTRA_ESTIMATE_ID, estimateId);
        startActivityForResult(intent, FRAG_RESERVED_ONE);
    }

    public void startCancelReservationActivity(int estimateId) {
        Intent intent = new Intent(getContext(), CancelReservationActivity.class);
        intent.putExtra(CancelReservationActivity.FRAG_NAME, "ReservationListFragment");
        intent.putExtra(CancelReservationActivity.EXTRA_ESTIMATE_ID, estimateId);
        startActivityForResult(intent, FRAG_RESERVATION_LIST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("ReservationMgmFragment", "requestCode : " + requestCode);
        if(requestCode == FRAG_RESERVATION_LIST){
            if(resultCode == Activity.RESULT_OK){
                Log.i("ReservationMgmFragment","cancelReservationFrag로부터 result_ok 왔다");
               pager.setCurrentItem(TAB_RESERVATION_LIST);
            }else{
                Log.i("ReservationMgmFragment","FragmentName 못받았다");
            }
        } else if(requestCode == FRAG_RESERVED_ONE){
            if(resultCode == Activity.RESULT_OK){
                Log.i("ReservationMgmFragment","paymentFrag로부터 result_ok 왔다");
                pager.setCurrentItem(TAB_RESERVED_ONE);
            }else{
                Log.i("ReservationMgmFragment","FragmentName 못받았다");
            }
        }
    }
}
