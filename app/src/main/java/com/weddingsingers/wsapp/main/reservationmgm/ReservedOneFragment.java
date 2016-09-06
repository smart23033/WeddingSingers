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
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.view.EstimateView;
import com.weddingsingers.wsapp.function.chatting.chatting.ChattingActivity;
import com.weddingsingers.wsapp.function.reservation.cancelreservation.CancelReservationActivity;
import com.weddingsingers.wsapp.function.schedulemgm.schedulemgm.CancelScheduleActivity;
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
    private static ReservedOneFragment instance;



    @BindView(R.id.reserved_one_rv_list)
    RecyclerView recyclerView;

    ReservedOneAdapter mAdapter;


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reserved_one, container, false);

        ButterKnife.bind(this,view);

        mAdapter = new ReservedOneAdapter();

        mAdapter.setOnAdapterChatBtnClickListener(new ReservedOneAdapter.OnAdapterChatBtnClickListener() {
            @Override
            public void onAdapterChatBtnClick(View view, Estimate profile, int position) {
                Intent intent = new Intent(getContext(), ChattingActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        mAdapter.setOnAdapterCancelBtnClickListener(new ReservedOneAdapter.OnAdapterCancelBtnClickListener() {
            @Override
            public void onAdapterCancelBtnClick(View view, Estimate estimate, int position) {
                Intent intent = new Intent(getContext(), CancelScheduleActivity.class);
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

    void init(){
        ReservedSingerRequest reservedSingerRequest = new ReservedSingerRequest(getContext(), TAB_RESERVED_ONE);
        NetworkManager.getInstance().getNetworkData(reservedSingerRequest, new NetworkManager.OnResultListener<NetworkResult<List<Estimate>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Estimate>>> request, NetworkResult<List<Estimate>> result) {

                for(Estimate e : result.getResult()){
                    Estimate estimate = new Estimate();
                    estimate.setSingerName(e.getSingerName());
                    estimate.setSingerImage(e.getSingerImage());
                    estimate.setDate(e.getDate());
                    estimate.setLocation(e.getLocation());
                    estimate.setSongs(e.getSongs());
                    estimate.setSpecial(e.getSpecial());
                    mAdapter.add(e);
                }

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<Estimate>>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

}
