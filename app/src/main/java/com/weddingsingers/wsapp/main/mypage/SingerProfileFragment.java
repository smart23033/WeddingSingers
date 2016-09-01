package com.weddingsingers.wsapp.main.mypage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.mypage.mypage.UserInfoActivity;
import com.weddingsingers.wsapp.function.mypage.singervideomgm.SingerProfileModifyActivity;
import com.weddingsingers.wsapp.function.mypage.singervideomgm.SingerVideoMgmActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerProfileFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";

    public SingerProfileFragment() {
        // Required empty public constructor
    }

    public static SingerProfileFragment newInstance(String message) {
        SingerProfileFragment fragment = new SingerProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_singer_profile, container, false);

        ButterKnife.bind(this, view);


        return view;
    }

    @OnClick(R.id.singer_profile_img_btn_modify)
    void OnModifyClick() {
        getContext().startActivity(new Intent(getActivity(), SingerProfileModifyActivity.class));
    }

    @OnClick(R.id.singer_profile_rl_my_video)
    void onMyVideoClick() {
        getContext().startActivity(new Intent(getActivity(), SingerVideoMgmActivity.class));
    }

}
