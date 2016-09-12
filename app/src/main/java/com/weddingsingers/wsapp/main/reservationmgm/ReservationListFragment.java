package com.weddingsingers.wsapp.main.reservationmgm;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.function.chatting.chatting.ChattingActivity;
import com.weddingsingers.wsapp.function.reservation.cancelreservation.CancelReservationActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.EstimateListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationListFragment extends Fragment {

    private static final int TAB_RESERVATION_LIST = 1;
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

        ButterKnife.bind(this, view);

        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnAdapterPayBtnClickListener(new ReservationListAdapter.OnAdapterPayBtnClickListener() {
            @Override
            public void onAdapterPayBtnClick(View view, Estimate estimate, int position) {
                int estimateId;
                estimateId = estimate.getId();
                ((ReservationMgmFragment) getParentFragment()).startPaymentActivity(estimateId);
            }
        });

        mAdapter.setOnAdapterChatBtnClickListener(new ReservationListAdapter.OnAdapterChatBtnClickListener() {
            @Override
            public void onAdapterChatBtnClick(View view, Estimate estimate, int position) {
                Intent intent = new Intent(getContext(), ChattingActivity.class);
                startActivity(intent);
            }
        });

        mAdapter.setOnAdapterCancelBtnClickListener(new ReservationListAdapter.OnAdapterCancelBtnClickListener() {
            @Override
            public void onAdapterCancelBtnClick(View view, Estimate estimate, int position) {
                Intent intent = new Intent(getContext(), CancelReservationActivity.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(manager);

        initData();

        return view;
    }

    private void initData() {

        EstimateListRequest estimateListRequest = new EstimateListRequest(getContext(), TAB_RESERVATION_LIST);
        NetworkManager.getInstance().getNetworkData(estimateListRequest, new NetworkManager.OnResultListener<NetworkResult<List<Estimate>>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<List<Estimate>>> request, NetworkResult<List<Estimate>> result) {

                        for (Estimate e : result.getResult()) {
                            Log.i("ReservationListFragment","e.getSingerImage() : " + e.getSingerImage());
                            Estimate estimate = new Estimate();
                            estimate.setId(e.getId());
                            estimate.setSingerName(e.getSingerName());
                            estimate.setSingerImage(e.getSingerImage());
                            estimate.setDate(e.getDate());
                            estimate.setLocation(e.getLocation());
                            estimate.setSongs(e.getSongs());
                            estimate.setStatus(e.getStatus());
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