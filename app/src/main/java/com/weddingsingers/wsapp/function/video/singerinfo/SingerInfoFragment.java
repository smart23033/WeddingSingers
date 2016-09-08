package com.weddingsingers.wsapp.function.video.singerinfo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Singer;
import com.weddingsingers.wsapp.data.view.ProfileView;
import com.weddingsingers.wsapp.function.video.othervideo.OtherVideoActivity;
import com.weddingsingers.wsapp.function.video.singerreview.SingerReviewActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.SingerMyProfileRequest;
import com.weddingsingers.wsapp.request.SingerProfileRequest;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerInfoFragment extends Fragment {

    final static int ARG_SIMPLE = 1;

    @BindView(R.id.singer_info_pv_profile)
    ProfileView singerProfileView;

    @BindView(R.id.singer_info_intro_tv_content)
    TextView descriptionView;

    @BindView(R.id.singer_info_price_tv_special_price)
    TextView specialView;

    @BindView(R.id.singer_info_price_tv_std_price)
    TextView standardView;

    @BindView(R.id.singer_info_song_tv_content)
    TextView songView;

    public SingerInfoFragment() {
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

        View view = inflater.inflate(R.layout.fragment_singer_info, container, false);

        ButterKnife.bind(this, view);

        initData();
        return view;
    }

    Singer singer;
    int singerId = 3;

    public void initData() {

        SingerProfileRequest singerProfileRequest = new SingerProfileRequest(getContext(), singerId);
        NetworkManager.getInstance().getNetworkData(singerProfileRequest, new NetworkManager.OnResultListener<NetworkResult<Singer>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Singer>> request, NetworkResult<Singer> result) {

                // 가격에 , 찍기
                NumberFormat nf = NumberFormat.getInstance();

                String singerName = result.getResult().getSingerName();
                String singerImage = result.getResult().getSingerImage();
                String comment = result.getResult().getComment();
                int standard = result.getResult().getStandard();
                int special = result.getResult().getSpecial();

                singerProfileView.setSingerImage(singerImage);
                singerProfileView.setSingerId(singerId);
                singerProfileView.setComment(comment);
                singerProfileView.setSingerName(singerName);
                singerProfileView.setSingerImage(singerImage);

                standardView.setText(nf.format(standard));
                specialView.setText(nf.format(special));

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
                for (String song : singer.getSongs())
                {
                    songList += song + "\n";
                }

                descriptionView.setText(singer.getDescription());
                songView.setText(songList);

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Singer>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });

    }

    @OnClick(R.id.singer_info_rl_review)
    public void onReviewClick() {
        Intent intent = new Intent(getActivity(), SingerReviewActivity.class);
        intent.putExtra("singerId", singerId);
        startActivity(intent);
    }

    @OnClick(R.id.singer_info_rl_other)
    public void onOtherVideoClick() {
        Intent intent = new Intent(getActivity(), OtherVideoActivity.class);
        intent.putExtra("singerId", singerId);
        startActivity(intent);
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
