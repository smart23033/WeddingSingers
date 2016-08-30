package com.weddingsingers.wsapp.main.mypage;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.mypage.accountmgm.AccountMgmActivity;
import com.weddingsingers.wsapp.function.mypage.favoritevideo.FavoriteVideoActivity;
import com.weddingsingers.wsapp.function.mypage.myinquiry.MyInquiryActivity;
import com.weddingsingers.wsapp.function.mypage.mypage.UserInfoActivity;
import com.weddingsingers.wsapp.function.mypage.pointstore.PointStoreActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyPageCustomerFragment extends Fragment {

    public MyPageCustomerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page_customer, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.my_page_customer_img_btn_modify)
    void onModifyClick() {
        getContext().startActivity(new Intent(getActivity(), UserInfoActivity.class));
    }

    @OnClick(R.id.my_page_customer_rl_inquiry)
    void onInquiryClick() {
        getContext().startActivity(new Intent(getActivity(), MyInquiryActivity.class));
    }

    @OnClick(R.id.my_page_customer_rl_favor)
    void onFavoriteVideoClick() {
        getContext().startActivity(new Intent(getActivity(), FavoriteVideoActivity.class));
    }

    @OnClick(R.id.my_page_customer_rl_account)
    void onAccountMgmClick() {
        getContext().startActivity(new Intent(getActivity(), AccountMgmActivity.class));
    }

    @OnClick(R.id.my_page_customer_rl_point)
    void onPointStoreClick() {
        getContext().startActivity(new Intent(getActivity(), PointStoreActivity.class));
    }


}
