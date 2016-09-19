package com.weddingsingers.wsapp.main.mypage;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.function.mypage.accountmgm.AccountMgmActivity;
import com.weddingsingers.wsapp.function.mypage.myinquiry.MyInquiryActivity;
import com.weddingsingers.wsapp.function.mypage.mypage.UserInfoActivity;
import com.weddingsingers.wsapp.function.mypage.statistic.StatisticActivity;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.manager.PropertyManager;
import com.weddingsingers.wsapp.request.LeaveRequest;
import com.weddingsingers.wsapp.request.LogOutRequest;
import com.weddingsingers.wsapp.request.ProfileRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerMyPageFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";

    public final static int TYPE_LOCAL = 1;
    public final static int TYPE_FACEBOOK = 2;

    @BindView(R.id.singer_my_page_riv_picture)
    RoundedImageView pictureView;

    @BindView(R.id.singer_my_page_tv_email)
    TextView emailView;

    @BindView(R.id.singer_my_page_tv_name)
    TextView nameView;

    @BindView(R.id.singer_my_page_tv_point_value)
    TextView pointView;

    LoginManager mLoginManager;

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

        mLoginManager = LoginManager.getInstance();

        pictureView.mutateBackground(true);
        pictureView.setOval(true);

        initData();

        return view;
    }

    int loginType;

    private void initData() {

        ProfileRequest request = new ProfileRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {

                User user = new User();
                user.setPhotoURL(result.getResult().getPhotoURL());
                user.setName(result.getResult().getName());
                user.setEmail(result.getResult().getEmail());
                user.setPoint(result.getResult().getPoint());

                loginType = result.getResult().getLoginType();

                Glide.with(getActivity())
                        .load(user.getPhotoURL())
                        .asBitmap()
                        .centerCrop()
                        .error(ContextCompat.getDrawable(getActivity(), R.drawable.ic_nav_logout))
                        .into(pictureView);

                nameView.setText(user.getName());
                emailView.setText(user.getEmail());
                pointView.setText(user.getPoint() + "P");

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "SingerMyPageFragment fail - " + errorMessage, Toast.LENGTH_SHORT).show();

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

    @OnClick(R.id.my_page_singer_img_btn_modify)
    void OnModifyClick() {
        Intent intent = new Intent(getActivity(), UserInfoActivity.class);
        startActivityForResult(intent, 1);
    }

    @OnClick(R.id.singer_my_page_rl_inquiry)
    void onInquiryClick() {
        getContext().startActivity(new Intent(getActivity(), MyInquiryActivity.class));
    }

    @OnClick(R.id.singer_my_page_rl_statistic)
    void onStatisticVideoClick() {
        getContext().startActivity(new Intent(getActivity(), StatisticActivity.class));
    }

    @OnClick(R.id.singer_my_page_rl_account)
    void onAccountMgmClick() {
        getContext().startActivity(new Intent(getActivity(), AccountMgmActivity.class));
    }

    private boolean isLogin() {
        AccessToken token = AccessToken.getCurrentAccessToken();
        return token != null;
    }

    @OnClick(R.id.singer_my_page_btn_logout)
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

    @OnClick(R.id.singer_my_page_btn_leave)
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