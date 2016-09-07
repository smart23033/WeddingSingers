package com.weddingsingers.wsapp.function.schedulemgm.schedulemgm;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.function.chatting.chatting.ChattingActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.EstimateListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailScheduleFragment extends Fragment {

    private static final int TAB_ESTIMATE_LIST = 1;

    final static String ARG_YEAR = "argYear";
    final static String ARG_MONTH = "argMonth";

    @BindView(R.id.detail_schedule_rv_list)
    RecyclerView recyclerView;

    @BindView(R.id.detail_schedule_tv_month)
    TextView monthView;

    ScheduleListAdapter mAdapter;

    public static DetailScheduleFragment newInstance(int year, int month) {
        DetailScheduleFragment fragment = new DetailScheduleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_YEAR, year);
        args.putInt(ARG_MONTH,month);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailScheduleFragment() {
        // Required empty public constructor
    }

    int year;
    int month;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            year = getArguments().getInt(ARG_YEAR);
            month = getArguments().getInt(ARG_MONTH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_schedule, container, false);

        ButterKnife.bind(this,view);

        String[] monthName = getResources().getStringArray(R.array.month);

        monthView.setText(monthName[month-1]);

        mAdapter = new ScheduleListAdapter();

        mAdapter.setOnAdapterCancelBtnClickListener(new ScheduleListAdapter.OnAdapterCancelBtnClickListener() {
            @Override
            public void onAdapterCancelBtnClick(View view, Estimate estimate, int position) {
                Intent intent = new Intent(getContext(), CancelScheduleActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        mAdapter.setOnAdapterChatBtnClickListener(new ScheduleListAdapter.OnAdapterChatBtnClickListener() {
            @Override
            public void onAdapterChatBtnClick(View view, Estimate estimate, int position) {
                Intent intent = new Intent(getContext(), ChattingActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

        init();

        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                getActivity().finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {

//        monthView.setText("" + monthName[month]);

        EstimateListRequest estimateListRequest = new EstimateListRequest(getContext(), TAB_ESTIMATE_LIST);
        NetworkManager.getInstance().getNetworkData(estimateListRequest, new NetworkManager.OnResultListener<NetworkResult<List<Estimate>>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<List<Estimate>>> request, NetworkResult<List<Estimate>> result) {

                        for (Estimate e : result.getResult()) {
                            Estimate estimate = new Estimate();
                            estimate.setId(e.getId());
                            estimate.setCustomerName(e.getCustomerName());
                            estimate.setCustomerImage(e.getCustomerImage());
                            estimate.setDate(e.getDate());
                            estimate.setLocation(e.getLocation());
                            estimate.setSongs(e.getSongs());
                            estimate.setSpecial(e.getSpecial());
                            mAdapter.add(e);
                        }

                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<List<Estimate>>> request,
                                       int errorCode, String errorMessage, Throwable e) {

                    }
                }

        );
    }

}
