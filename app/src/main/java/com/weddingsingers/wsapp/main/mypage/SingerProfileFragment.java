package com.weddingsingers.wsapp.main.mypage;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Singer;
import com.weddingsingers.wsapp.function.mypage.mypage.SingerProfileModifyActivity;
import com.weddingsingers.wsapp.function.mypage.singervideomgm.SingerVideoMgmActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.SingerMyProfileRequest;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerProfileFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";

    @BindView(R.id.singer_profile_tv_basic_name)
    TextView nameView;

    @BindView(R.id.singer_profile_tv_basic_comment)
    TextView commentView;

    @BindView(R.id.singer_profile_tv_basic_location)
    TextView locationView;

    @BindView(R.id.singer_profile_tv_basic_composition)
    TextView compositionView;

    @BindView(R.id.singer_profile_tv_basic_theme)
    TextView themeView;

    @BindView(R.id.singer_profile_intro_tv_content)
    TextView introView;

    @BindView(R.id.singer_profile_price_tv_special_price)
    TextView specialPriceView;

    @BindView(R.id.singer_profile_price_tv_std_price)
    TextView standardPriceView;

    @BindView(R.id.singer_profile_song_tv_song)
    TextView songView;

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

        initData();

        return view;
    }

    Singer singer;

    private void initData() {

        SingerMyProfileRequest request = new SingerMyProfileRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Singer>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Singer>> request, NetworkResult<Singer> result) {

                singer = new Singer();
                singer.setSingerName(result.getResult().getSingerName());
                singer.setComment(result.getResult().getComment());
                singer.setLocation(result.getResult().getLocation());
                singer.setComposition(result.getResult().getComposition());
                singer.setTheme(result.getResult().getTheme());
                singer.setDescription(result.getResult().getDescription());
                singer.setSpecial(result.getResult().getSpecial());
                singer.setStandard(result.getResult().getStandard());
                singer.setSongs(result.getResult().getSongs());

                // 곡목록 배열 -> string 으로
                String songList = "";
                int i = 0;
                for (String song : singer.getSongs())
                {
                    songList += song;
                    if (i < (singer.getSongs().size() - 1)) {
                        songList += "\n";
                    }
                    i++;
                }

                // 가격에 , 찍기
                NumberFormat nf = NumberFormat.getInstance();

                String[] locationItems = getResources().getStringArray(R.array.location);
                String[] compositionItems = getResources().getStringArray(R.array.composition);
                String[] themeItems = getResources().getStringArray(R.array.theme);

                nameView.setText(singer.getSingerName());
                commentView.setText(singer.getComment());
                locationView.setText(locationItems[singer.getLocation()]);
                compositionView.setText(compositionItems[singer.getComposition()]);
                themeView.setText(themeItems[singer.getTheme()]);
                introView.setText(singer.getDescription());
                specialPriceView.setText("￦ " + nf.format(singer.getSpecial()));
                standardPriceView.setText("￦ " + nf.format(singer.getStandard()));
                songView.setText(songList);

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Singer>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getActivity(), "SingerProfileFragment fail - " + errorMessage, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @OnClick(R.id.singer_profile_img_btn_modify)
    void OnModifyClick() {
        Intent intent = new Intent(getActivity(), SingerProfileModifyActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
//            Toast.makeText(getActivity(), "ok" + resultCode, Toast.LENGTH_SHORT).show();
            initData();
        }
    }

    @OnClick(R.id.singer_profile_rl_my_video)
    void onMyVideoClick() {
        getContext().startActivity(new Intent(getActivity(), SingerVideoMgmActivity.class));
    }

}
