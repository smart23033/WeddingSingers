package com.weddingsingers.wsapp.main.mypage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.data.Video;
import com.weddingsingers.wsapp.function.mypage.accountmgm.AccountMgmActivity;
import com.weddingsingers.wsapp.function.mypage.favoritevideo.FavoriteVideoActivity;
import com.weddingsingers.wsapp.function.mypage.myinquiry.MyInquiryActivity;
import com.weddingsingers.wsapp.function.mypage.mypage.UserInfoActivity;
import com.weddingsingers.wsapp.function.mypage.pointstore.PointStoreActivity;
import com.weddingsingers.wsapp.function.mypage.statistic.StatisticActivity;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.MyPageRequest;
import com.weddingsingers.wsapp.request.VideoRequest;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerMyPageFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";

    public SingerMyPageFragment() {
        // Required empty public constructor
    }

    public static SingerMyPageFragment newInstance(String message) {
        SingerMyPageFragment fragment = new SingerMyPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_singer_my_page, container, false);

        ButterKnife.bind(this, view);

        Toast.makeText(getActivity(), "id : " + MainActivity.isLogin, Toast.LENGTH_SHORT).show();
        initData();

        return view;
    }

    private void initData() {

        MyPageRequest request = new MyPageRequest(getContext(), MainActivity.isLogin);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                Toast.makeText(getActivity(), result.getResult().getName(), Toast.LENGTH_SHORT).show();
                User user = new User();
                user.setName(result.getResult().getName());


//                Video video = new Video();

//                video.setDate(result.getResult().getDate());
//                video.setSingerName(result.getResult().getSingerName());
//                video.setId(result.getResult().getId());
//                video.setFavorite(result.getResult().getFavorite());
//                video.setComment(result.getResult().getComment());
//                video.setHit(result.getResult().getHit());
//                video.setRating(result.getResult().getRating());
//                video.setReview(result.getResult().getReview());
//                video.setStandard(result.getResult().getStandard());
//                video.setSpecial(result.getResult().getSpecial());
//                video.setTitle(result.getResult().getTitle());
//                video.setUrl(result.getResult().getUrl());
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "SingerMyPageFragment failure", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @OnClick(R.id.my_page_singer_img_btn_modify)
    void OnModifyClick() {
        getContext().startActivity(new Intent(getActivity(), UserInfoActivity.class));
    }

    @OnClick(R.id.singer_my_page_rl_inquiry)
    void onInquiryClick() {
        getContext().startActivity(new Intent(getActivity(), MyInquiryActivity.class));
    }

    @OnClick(R.id.singer_my_page_rl_statistic)
    void onFavoriteVideoClick() {
        getContext().startActivity(new Intent(getActivity(), StatisticActivity.class));
    }

    @OnClick(R.id.singer_my_page_rl_account)
    void onAccountMgmClick() {
        getContext().startActivity(new Intent(getActivity(), AccountMgmActivity.class));
    }
}
