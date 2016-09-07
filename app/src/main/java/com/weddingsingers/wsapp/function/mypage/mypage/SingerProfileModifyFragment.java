package com.weddingsingers.wsapp.function.mypage.mypage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Singer;
import com.weddingsingers.wsapp.function.mypage.mypage.SingerProfileModifyAdapter;
import com.weddingsingers.wsapp.function.search.search.FilterSpinnerAdapter;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.ModifyUserRequest;
import com.weddingsingers.wsapp.request.SingerProfileSettingRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerProfileModifyFragment extends Fragment {

    @BindView(R.id.singer_profile_modify_et_comment)
    EditText commentView;

    @BindView(R.id.singer_profile_modify_et_intro)
    EditText introView;

    @BindView(R.id.singer_profile_modify_et_special)
    EditText specialView;

    @BindView(R.id.singer_profile_modify_et_standard)
    EditText standardView;

    @BindView(R.id.singer_profile_modify_sp_location)
    Spinner locationSpinner;

    @BindView(R.id.singer_profile_modify_sp_composition)
    Spinner compositionSpinner;

    @BindView(R.id.singer_profile_modify_sp_theme)
    Spinner themeSpinner;

    FilterSpinnerAdapter locationAdapter;
    FilterSpinnerAdapter compositionAdapter;
    FilterSpinnerAdapter themeAdapter;

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

        locationAdapter = new FilterSpinnerAdapter();
        compositionAdapter = new FilterSpinnerAdapter();
        themeAdapter = new FilterSpinnerAdapter();

        locationSpinner.setAdapter(locationAdapter);
        compositionSpinner.setAdapter(compositionAdapter);
        themeSpinner.setAdapter(themeAdapter);

        initData();

        return view;
    }

    @OnItemSelected(R.id.singer_profile_modify_sp_location)
    void onLocationItemSelected(int position){
        singer.setLocation(locationSpinner.getSelectedItem().toString());
    }

    @OnItemSelected(R.id.singer_profile_modify_sp_composition)
    void onCompositionSelected(int position){
        singer.setComposition(compositionSpinner.getSelectedItem().toString());
    }

    @OnItemSelected(R.id.singer_profile_modify_sp_theme)
    void onThemeItemSelected(int position){
        singer.setTheme(themeSpinner.getSelectedItem().toString());
    }

    Singer singer = new Singer();

    @OnClick(R.id.singer_profile_modify_btn_apply)
    public void onApplyClick() {

        singer.setComment(commentView.getText().toString());
        singer.setDescription(introView.getText().toString());
        singer.setSpecial(Integer.parseInt(specialView.getText().toString()));
        singer.setStandard(Integer.parseInt(standardView.getText().toString()));

        SingerProfileSettingRequest request = new SingerProfileSettingRequest(getContext(), singer);

        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Boolean>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Boolean>> request, NetworkResult<Boolean> result) {

                Toast.makeText(getActivity(), "success code : " + result.getCode(), Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Boolean>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getActivity(), "SingerMyPageFragment fail - " + errorMessage, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @OnClick(R.id.singer_profile_modify_btn_cancel)
    public void onCancelClick() {
        getActivity().finish();
    }

    private void initData(){
        locationAdapter.clear();
        compositionAdapter.clear();
        themeAdapter.clear();

        String[] items = getResources().getStringArray(R.array.location);
        locationAdapter.addAll(items);
        items = getResources().getStringArray(R.array.composition);
        compositionAdapter.addAll(items);
        items = getResources().getStringArray(R.array.theme);
        themeAdapter.addAll(items);
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
