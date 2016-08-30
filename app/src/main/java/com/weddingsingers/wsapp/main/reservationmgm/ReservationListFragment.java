package com.weddingsingers.wsapp.main.reservationmgm;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SearchResult;
import com.weddingsingers.wsapp.data.SingerList;
import com.weddingsingers.wsapp.function.chatting.chatting.ChattingActivity;
import com.weddingsingers.wsapp.function.payment.payment.PaymentActivity;
import com.weddingsingers.wsapp.function.reservation.cancelreservation.CancelReservationActivity;
import com.weddingsingers.wsapp.function.video.video.VideoActivity;
import com.weddingsingers.wsapp.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationListFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";
    private String message;
    private static ReservationListFragment instance;

    public ReservationListFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.reservation_list_rv_list)
    RecyclerView recyclerView;

    ReservationListAdapter mAdapter;

    public static ReservationListFragment newInstance(String message) {
        ReservationListFragment fragment = new ReservationListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ReservationListAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservation_list, container, false);

        ButterKnife.bind(this,view);

        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnAdapterPayBtnClickListener(new ReservationListAdapter.OnAdapterPayBtnClickListener() {
            @Override
            public void onAdapterPayBtnClick(View view, SingerList singerList, int position) {;
                ((ReservationMgmFragment)getParentFragment()).startPaymentActivity();
            }
        });

        mAdapter.setOnAdapterChatBtnClickListener(new ReservationListAdapter.OnAdapterChatBtnClickListener() {
            @Override
            public void onAdapterChatBtnClick(View view, SingerList singerList, int position) {
                Intent intent = new Intent(getContext(), ChattingActivity.class);
                startActivity(intent);
            }
        });

        mAdapter.setOnAdapterCancelBtnClickListener(new ReservationListAdapter.OnAdapterCancelBtnClickListener() {
            @Override
            public void onAdapterCancelBtnClick(View view, SingerList singerList, int position) {
                Intent intent = new Intent(getContext(), CancelReservationActivity.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager);

        initData();

        return view;
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            SingerList singerList = new SingerList();
            singerList.setLocation("Seoul");
            singerList.setDate("2016. 4. 26");
            singerList.setSingerName("singer name");
            singerList.setSongs("Clarity - Zedd");
            mAdapter.add(singerList);
        }
    }

}
