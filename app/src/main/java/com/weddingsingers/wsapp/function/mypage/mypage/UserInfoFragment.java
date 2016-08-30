package com.weddingsingers.wsapp.function.mypage.mypage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends Fragment {


    public UserInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_info, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.user_info_btn_apply)
    public void onApplyClick() {
        getActivity().finish();
    }

    @OnClick(R.id.user_info_btn_cancel)
    public void onCancelClick() {
        getActivity().finish();
    }



}
