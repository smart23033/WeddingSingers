package com.weddingsingers.wsapp.main.reservationmgm;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.view.LargeProfileView;
import com.weddingsingers.wsapp.function.chatting.chatting.ChattingActivity;
import com.weddingsingers.wsapp.function.reservation.cancelreservation.CancelReservationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservedOneFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";
    private String message;
    private static ReservedOneFragment instance;

    public ReservedOneFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.reserved_one_lpv_profile)
    LargeProfileView singerView;

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

        return view;
    }

    @OnClick(R.id.reserved_one_btn_cancel)
    void onCancelBtnClicked(){
        startActivity(new Intent(getActivity(),CancelReservationActivity.class));
    }

    @OnClick(R.id.reserved_one_btn_chat)
    void onChatBtnClicked(){
        startActivity(new Intent(getActivity(), ChattingActivity.class));
        getActivity().finish();
    }

}
