package com.weddingsingers.wsapp.main.mypage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.mypage.accountmgm.AccountMgmActivity;
import com.weddingsingers.wsapp.function.mypage.favoritevideo.FavoriteVideoActivity;
import com.weddingsingers.wsapp.function.mypage.myinquiry.MyInquiryActivity;
import com.weddingsingers.wsapp.function.mypage.mypage.UserInfoActivity;
import com.weddingsingers.wsapp.function.mypage.pointstore.PointStoreActivity;

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

        return view;
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
        getContext().startActivity(new Intent(getActivity(), FavoriteVideoActivity.class));
    }

    @OnClick(R.id.singer_my_page_rl_account)
    void onAccountMgmClick() {
        getContext().startActivity(new Intent(getActivity(), AccountMgmActivity.class));
    }
}
