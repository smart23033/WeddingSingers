package com.weddingsingers.wsapp.main.mypage;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.function.mypage.accountmgm.AccountMgmActivity;
import com.weddingsingers.wsapp.function.mypage.favoritevideo.FavoriteVideoActivity;
import com.weddingsingers.wsapp.function.mypage.myinquiry.MyInquiryActivity;
import com.weddingsingers.wsapp.function.mypage.mypage.UserInfoActivity;
import com.weddingsingers.wsapp.function.mypage.mypoint.MyPointActivity;
import com.weddingsingers.wsapp.function.mypage.pointstore.PointStoreActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.MyPageRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyPageCustomerFragment extends Fragment {

    @BindView(R.id.my_page_customer_riv_picture)
    RoundedImageView pictureView;

    @BindView(R.id.my_page_customer_tv_email)
    TextView emailView;

    @BindView(R.id.my_page_customer_tv_name)
    TextView nameView;

    @BindView(R.id.my_page_customer_tv_point)
    TextView pointView;

    public MyPageCustomerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page_customer, container, false);

        ButterKnife.bind(this, view);

        pictureView.mutateBackground(true);
        pictureView.setOval(true);

        initData();

        return view;
    }

    private void initData() {

        MyPageRequest request = new MyPageRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {

                User user = new User();
                user.setPhotoURL(result.getResult().getPhotoURL());
                user.setName(result.getResult().getName());
                user.setEmail(result.getResult().getEmail());
                user.setPoint(result.getResult().getPoint());

                Glide.with(getActivity())
                        .load(user.getPhotoURL())
                        .centerCrop()
                        .crossFade()
                        .placeholder(ContextCompat.getDrawable(getActivity(), R.drawable.ic_nav_logout))
                        .into(pictureView);

                nameView.setText(user.getName());
                emailView.setText(user.getEmail());
                pointView.setText(user.getPoint() + "P");

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "SingerMyPageFragment failure", Toast.LENGTH_SHORT).show();

            }
        });
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

    @OnClick(R.id.my_page_customer_rl_my_point)
    void onMyPointStoreClick() {
        getContext().startActivity(new Intent(getActivity(), MyPointActivity.class));
    }


}
