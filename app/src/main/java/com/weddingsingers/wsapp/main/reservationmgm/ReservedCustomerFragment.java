package com.weddingsingers.wsapp.main.reservationmgm;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.function.chatting.chatting.ChattingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservedCustomerFragment extends Fragment {

    @BindView(R.id.reserved_customer_rv_list)
    RecyclerView recyclerView;

    ReservedCustomerListAdapter mAdapter;

    public ReservedCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reserved_customer, container, false);

        ButterKnife.bind(this,view);

        mAdapter = new ReservedCustomerListAdapter();

        mAdapter.setOnAdapterChatBtnClickListener(new ReservedCustomerListAdapter.OnAdapterChatBtnClickListener() {
            @Override
            public void onAdapterChatBtnClick(View view, Estimate estimate, int position) {
                Intent intent = new Intent(getContext(), ChattingActivity.class);
                startActivity(intent);
            }
        });

        mAdapter.setOnAdapterResponseBtnClickListener(new ReservedCustomerListAdapter.OnAdapterResponseBtnClickListener() {
            @Override
            public void onAdapterResponseBtnClick(View view, Estimate profile, int position) {

            }
        });

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

        initData();

        return view;
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            Estimate estimate = new Estimate();
            estimate.setLocation("Seoul");
            estimate.setDate("2016. 4. 26");
            estimate.setCustomerName("customer name");
            estimate.setSongs("Thriller - Michael Jackson");
            estimate.setSpecial("special Request");
            mAdapter.add(estimate);
        }
    }
}
