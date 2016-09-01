package com.weddingsingers.wsapp.function.mypage.singervideomgm;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SingerVideoMgm;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerProfileModifyFragment extends Fragment {

    @BindView(R.id.singer_profile_modify_sp_location)
    Spinner locationSpinner;

    @BindView(R.id.singer_profile_modify_sp_composition)
    Spinner compositionSpinner;

    @BindView(R.id.singer_profile_modify_sp_theme)
    Spinner themeSpinner;

    SingerProfileModifyAdapter mAdapter;

    public SingerProfileModifyFragment() {
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
        View view = inflater.inflate(R.layout.fragment_singer_profile_modify, container, false);

        ButterKnife.bind(this, view);

        mAdapter = new SingerProfileModifyAdapter();
        locationSpinner.setAdapter(mAdapter);
        compositionSpinner.setAdapter(mAdapter);
        themeSpinner.setAdapter(mAdapter);
        locationSpinner.setSelection(0);

        initData();

        return view;
    }

    @OnItemSelected(R.id.singer_profile_modify_sp_location)
    void onLocationItemSelected(int position){
        locationSpinner.setSelection(position);
    }

    @OnItemSelected(R.id.singer_profile_modify_sp_composition)
    void onCompositionSelected(int position){
        compositionSpinner.setSelection(position);
    }

    @OnItemSelected(R.id.singer_profile_modify_sp_theme)
    void onThemeItemSelected(int position){
        themeSpinner.setSelection(position);
    }

    @OnClick(R.id.singer_profile_modify_btn_apply)
    public void onApplyClick() {
        getActivity().finish();
    }

    @OnClick(R.id.singer_profile_modify_btn_cancel)
    public void onCancelClick() {
        getActivity().finish();
    }

    private void initData(){
        String[] items = getResources().getStringArray(R.array.items);
        mAdapter.addAll(items);
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

}
