package com.weddingsingers.wsapp.main.mypage;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Singer;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.function.mypage.accountmgm.AccountMgmActivity;
import com.weddingsingers.wsapp.function.mypage.favoritevideo.FavoriteVideoActivity;
import com.weddingsingers.wsapp.function.mypage.myinquiry.MyInquiryActivity;
import com.weddingsingers.wsapp.function.mypage.mypage.UserInfoActivity;
import com.weddingsingers.wsapp.function.mypage.mypoint.MyPointActivity;
import com.weddingsingers.wsapp.function.mypage.pointstore.PointStoreActivity;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.manager.PropertyManager;
import com.weddingsingers.wsapp.request.LeaveRequest;
import com.weddingsingers.wsapp.request.LogOutRequest;
import com.weddingsingers.wsapp.request.MyPageRequest;
import com.weddingsingers.wsapp.request.SingerProfileRequest;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyPageCustomerFragment extends Fragment {

    public final static int TYPE_LOCAL = 1;
    public final static int TYPE_FACEBOOK = 2;

    @BindView(R.id.my_page_customer_riv_picture)
    RoundedImageView pictureView;

    @BindView(R.id.my_page_customer_tv_email)
    TextView emailView;

    @BindView(R.id.my_page_customer_tv_name)
    TextView nameView;

    @BindView(R.id.my_page_customer_tv_point)
    TextView pointView;

    LoginManager mLoginManager;

    public MyPageCustomerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page_customer, container, false);

        ButterKnife.bind(this, view);

        mLoginManager = LoginManager.getInstance();

        pictureView.mutateBackground(true);
        pictureView.setOval(true);

        initData();

        return view;
    }

    int loginType;

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

                loginType = result.getResult().getLoginType();

                Log.i("MyPageCustomerFragment", "loginType : " + loginType);

                Glide.with(getActivity())
                        .load(user.getPhotoURL())
                        .asBitmap()
                        .centerCrop()
                        .error(ContextCompat.getDrawable(getActivity(), R.drawable.login_ic_01))
                        .into(pictureView);

                nameView.setText(user.getName());
                emailView.setText(user.getEmail());
                pointView.setText(user.getPoint() + "P");

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "MypageCusomterFragment failure", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Toast.makeText(getActivity(), "ok : " + resultCode, Toast.LENGTH_SHORT).show();
            initData();
        }
    }

    @OnClick(R.id.my_page_customer_img_btn_modify)
    void onModifyClick() {
        Intent intent = new Intent(getActivity(), UserInfoActivity.class);
        startActivityForResult(intent, 1);
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

    private boolean isLogin() {
        AccessToken token = AccessToken.getCurrentAccessToken();
        return token != null;
    }

    @OnClick(R.id.my_page_customer_btn_logout)
    void onLogOutClick() {

        LogOutRequest logOutRequest = new LogOutRequest(getContext());
        NetworkManager.getInstance().getNetworkData(logOutRequest, new NetworkManager.OnResultListener<NetworkResult<String>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                if (loginType == TYPE_FACEBOOK && isLogin()) {
                    mLoginManager.logOut();

                }

                PropertyManager.getInstance().setEmail("");
                PropertyManager.getInstance().setPassword("");
                PropertyManager.getInstance().setFacebookId("");

                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getActivity(), "Logout request fail..", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @OnClick(R.id.my_page_customer_btn_leave)
    void onLeaveClick() {

        LeaveRequest leaveRequest = new LeaveRequest(getContext());
        NetworkManager.getInstance().getNetworkData(leaveRequest, new NetworkManager.OnResultListener<NetworkResult<String>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                PropertyManager.getInstance().setEmail("");
                PropertyManager.getInstance().setPassword("");
                PropertyManager.getInstance().setFacebookId("");
                //LoginManager.getInstance().logOut();
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getActivity(), "Leave request fail..", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
