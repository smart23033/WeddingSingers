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

    @BindView(R.id.singer_profile_sp_location)
    Spinner locationSpinner;

    @BindView(R.id.singer_profile_sp_composition)
    Spinner compositionSpinner;

    @BindView(R.id.singer_profile_sp_theme)
    Spinner themeSpinner;

    SingerProfileAdapter mAdapter;

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

        Button btn = (Button) view.findViewById(R.id.view_profile_btn_reserve);
        btn.setVisibility(View.GONE);

        ButterKnife.bind(this, view);

        mAdapter = new SingerProfileAdapter();
        locationSpinner.setAdapter(mAdapter);
        compositionSpinner.setAdapter(mAdapter);
        themeSpinner.setAdapter(mAdapter);
        locationSpinner.setSelection(0);

        initData();

        return view;
    }

    @OnClick(R.id.singer_profile_rl_my_video)
    void onMyVideoClick() {
        getContext().startActivity(new Intent(getActivity(), SingerVideoMgmActivity.class));
    }

    @OnItemSelected(R.id.singer_profile_sp_location)
    void onLocationItemSelected(int position){
        locationSpinner.setSelection(position);
    }

    @OnItemSelected(R.id.singer_profile_sp_composition)
    void onCompositionSelected(int position){
        compositionSpinner.setSelection(position);
    }

    @OnItemSelected(R.id.singer_profile_sp_theme)
    void onThemeItemSelected(int position){
        themeSpinner.setSelection(position);
    }

    private void initData(){
        String[] items = getResources().getStringArray(R.array.items);
        mAdapter.addAll(items);
    }

}
