package com.weddingsingers.wsapp.main.reservationmgm;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.view.EstimateView;
import com.weddingsingers.wsapp.function.chatting.chatting.ChattingActivity;
import com.weddingsingers.wsapp.function.reservation.cancelreservation.CancelReservationActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.ReservedSingerRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservedOneFragment extends Fragment {


    private static final int TAB_RESERVED_ONE = 2;

    private static final String ARG_MESSAGE = "param1";
    private String message;
    private static ReservedOneFragment instance;

    public ReservedOneFragment() {
        // Required empty public constructor
    }

    public static ReservedOneFragment newInstance(String message) {
        ReservedOneFragment fragment = new ReservedOneFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.reserved_one_ev_profile)
    EstimateView estimateView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reserved_one, container, false);

        ButterKnife.bind(this,view);

        init();

        return view;
    }

    void init(){
        ReservedSingerRequest reservedSingerRequest = new ReservedSingerRequest(getContext(), TAB_RESERVED_ONE);
        NetworkManager.getInstance().getNetworkData(reservedSingerRequest, new NetworkManager.OnResultListener<NetworkResult<List<Estimate>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Estimate>>> request, NetworkResult<List<Estimate>> result) {

//                리싸이클러만들고 다시와라

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<Estimate>>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

    @OnClick(R.id.reserved_one_btn_cancel)
    void onCancelBtnClick(){
        startActivity(new Intent(getActivity(),CancelReservationActivity.class));
    }

    @OnClick(R.id.reserved_one_btn_chat)
    void onChatBtnClick(){
        startActivity(new Intent(getActivity(), ChattingActivity.class));
    }

}
