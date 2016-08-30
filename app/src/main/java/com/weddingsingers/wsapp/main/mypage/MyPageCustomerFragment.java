package com.weddingsingers.wsapp.main.mypage;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.mypage.accountmgm.AccountMgmActivity;
import com.weddingsingers.wsapp.function.mypage.favoritevideo.FavoriteVideoActivity;
import com.weddingsingers.wsapp.function.mypage.myinquiry.MyInquiryActivity;
import com.weddingsingers.wsapp.function.mypage.mypage.UserInfoActivity;
import com.weddingsingers.wsapp.function.mypage.pointstore.PointStoreActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyPageCustomerFragment extends Fragment {

    public MyPageCustomerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page_customer, container, false);

        ButterKnife.bind(view);

//        @BindView(R.id.my_page_customer_img_btn_modify)
        ImageButton modifyBtn = (ImageButton) view.findViewById(R.id.my_page_customer_img_btn_modify);

        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getActivity(), UserInfoActivity.class));
            }
        });

        return view;
    }


}
