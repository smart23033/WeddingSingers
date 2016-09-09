package com.weddingsingers.wsapp.function.mypage.mypage;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Singer;
import com.weddingsingers.wsapp.function.search.search.FilterSpinnerAdapter;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.SingerMyProfileRequest;
import com.weddingsingers.wsapp.request.SingerProfileSettingRequest;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerProfileModifyFragment extends Fragment {

    @BindView(R.id.singer_profile_modify_et_comment)
    EditText commentInput;

    @BindView(R.id.singer_profile_modify_et_intro)
    EditText descriptionInput;

    @BindView(R.id.singer_profile_modify_et_special)
    EditText specialInput;

    @BindView(R.id.singer_profile_modify_et_standard)
    EditText standardInput;

    @BindView(R.id.singer_profile_modify_sp_location)
    Spinner locationSpinner;

    @BindView(R.id.singer_profile_modify_sp_composition)
    Spinner compositionSpinner;

    @BindView(R.id.singer_profile_modify_sp_theme)
    Spinner themeSpinner;

    @BindView(R.id.singer_profile_modify_et_songs)
    EditText songsInput;

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
    void onLocationItemSelected(int position) {
        
    }

    @OnItemSelected(R.id.singer_profile_modify_sp_composition)
    void onCompositionSelected(int position) {

    }

    @OnItemSelected(R.id.singer_profile_modify_sp_theme)
    void onThemeItemSelected(int position) {

    }

    @OnClick(R.id.singer_profile_modify_btn_apply)
    public void onApplyClick() {
        String[] songsTemp = songsInput.getText().toString().split(",");

        ArrayList<String> songs = new ArrayList<>();
        for (String item : songsTemp) {
            songs.add(item);
        }

        singer.setComment(commentInput.getText().toString());
        singer.setDescription(descriptionInput.getText().toString());
        singer.setSpecial(Integer.parseInt(specialInput.getText().toString()));
        singer.setStandard(Integer.parseInt(standardInput.getText().toString()));
        singer.setLocation(locationSpinner.getSelectedItemPosition());
        singer.setComposition(compositionSpinner.getSelectedItemPosition());
        singer.setTheme(themeSpinner.getSelectedItemPosition());
        singer.setSongs(songs);

        SingerProfileSettingRequest request = new SingerProfileSettingRequest(getContext(), singer);

        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Boolean>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Boolean>> request, NetworkResult<Boolean> result) {

                Toast.makeText(getActivity(), "success code : " + result.getCode(), Toast.LENGTH_SHORT).show();

                getActivity().setResult(Activity.RESULT_OK, new Intent());
                getActivity().finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Boolean>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getActivity(), "SingerMyPageFragment fail - " + errorMessage, Toast.LENGTH_SHORT).show();

            }
        });

    }

    Singer singer = new Singer();
    Singer singerGet = new Singer();

    @OnClick(R.id.singer_profile_modify_btn_cancel)
    public void onCancelClick() {
        getActivity().finish();
    }

    private void initData() {
        locationAdapter.clear();
        compositionAdapter.clear();
        themeAdapter.clear();

        String[] items = getResources().getStringArray(R.array.location);
        locationAdapter.addAll(items);
        items = getResources().getStringArray(R.array.composition);
        compositionAdapter.addAll(items);
        items = getResources().getStringArray(R.array.theme);
        themeAdapter.addAll(items);

        SingerMyProfileRequest request = new SingerMyProfileRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Singer>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Singer>> request, NetworkResult<Singer> result) {

                singerGet.setSingerName(result.getResult().getSingerName());
                singerGet.setComment(result.getResult().getComment());
                singerGet.setLocation(result.getResult().getLocation());
                singerGet.setComposition(result.getResult().getComposition());
                singerGet.setTheme(result.getResult().getTheme());
                singerGet.setDescription(result.getResult().getDescription());
                singerGet.setSpecial(result.getResult().getSpecial());
                singerGet.setStandard(result.getResult().getStandard());
                singerGet.setSongs(result.getResult().getSongs());

                String song = "";

                int i = 0;
                String comma = ",";
                for (String item : singerGet.getSongs())
                {
                    if(i == (singerGet.getSongs().size() - 1)) {
                        comma = "";
                    }
                    song += item + comma;
                    i++;
                }

                // 가격에 , 찍기
                NumberFormat nf = NumberFormat.getInstance();

                commentInput.setText(singerGet.getComment());
                locationSpinner.setSelection(singerGet.getLocation());
                compositionSpinner.setSelection(singerGet.getComposition());
                themeSpinner.setSelection(singerGet.getTheme());
                descriptionInput.setText(singerGet.getDescription());
                specialInput.setText("" + singerGet.getSpecial());
                standardInput.setText("" + singerGet.getStandard());
                songsInput.setText(song);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Singer>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getActivity(), "SingerProfileFragment fail - " + errorMessage, Toast.LENGTH_SHORT).show();

            }
        });
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
