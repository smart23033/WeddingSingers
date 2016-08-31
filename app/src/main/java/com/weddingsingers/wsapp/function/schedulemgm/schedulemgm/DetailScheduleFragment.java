package com.weddingsingers.wsapp.function.schedulemgm.schedulemgm;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.function.chatting.chatting.ChattingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailScheduleFragment extends Fragment {

    @BindView(R.id.detail_schedule_rv_list)
    RecyclerView recyclerView;

    ScheduleListAdapter mAdapter;

    public DetailScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_schedule, container, false);

        ButterKnife.bind(this,view);

        mAdapter = new ScheduleListAdapter();

        mAdapter.setOnAdapterCancelBtnClickListener(new ScheduleListAdapter.OnAdapterCancelBtnClickListener() {
            @Override
            public void onAdapterCancelBtnClick(View view, Estimate profile, int position) {
                Intent intent = new Intent(getContext(), CancelScheduleActivity.class);
                startActivity(intent);
            }
        });

        mAdapter.setOnAdapterChatBtnClickListener(new ScheduleListAdapter.OnAdapterChatBtnClickListener() {
            @Override
            public void onAdapterChatBtnClick(View view, Estimate profile, int position) {
                Intent intent = new Intent(getContext(), ChattingActivity.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

        initData();

        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Toast.makeText(getContext(), "DetailScheduleActivity's HomeAsUp Clicked", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
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
