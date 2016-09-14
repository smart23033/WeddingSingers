package com.weddingsingers.wsapp.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.DefaultAudience;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.FacebookLoginRequest;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginIntroFragment extends Fragment {

    public LoginIntroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    CallbackManager callbackManager;
    LoginManager mLoginManager;

    @BindView(R.id.login_intro_btn_facebook_login)
    Button facebookLoginBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_intro, container, false);

        ButterKnife.bind(this, view);

        mLoginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();

        return view;
    }

    private void loginFacebook() {
        mLoginManager.setDefaultAudience(DefaultAudience.FRIENDS);
        mLoginManager.setLoginBehavior(LoginBehavior.NATIVE_WITH_FALLBACK);
        mLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                FacebookLoginRequest facebookLoginRequest = new FacebookLoginRequest(getContext(),accessToken.getToken());
                NetworkManager.getInstance().getNetworkData(facebookLoginRequest, new NetworkManager.OnResultListener<NetworkResult<User>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                        Toast.makeText(getContext(),"FacebookLoginRequest success",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(getContext(),"FacebookLoginRequest fail : " + errorMessage,Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        mLoginManager.logInWithReadPermissions(this, Arrays.asList("email"));
    }

    @OnClick(R.id.login_intro_btn_facebook_login)
    void onFacebookLoginBtnClick(){
            loginFacebook();
    }

    @OnClick(R.id.login_intro_btn_login)
    void onLoginBtnClick() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager()
                .beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        ft.add(R.id.act_login_container, loginFragment, LoginActivity.FRAG_LOGIN);
        ft.addToBackStack(null);
        ft.commit();
    }

    User user;

    @OnClick(R.id.login_intro_btn_sign_up)
    void onSignUpBtnClick() {
        user = new User();
        FragmentTransaction ft = getActivity().getSupportFragmentManager()
                .beginTransaction();
        SignUpFirstFragment signUpFirstFragment = SignUpFirstFragment.newInstance(user);
        ft.add(R.id.act_login_container, signUpFirstFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
