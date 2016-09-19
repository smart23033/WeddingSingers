package com.weddingsingers.wsapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.manager.PropertyManager;
import com.weddingsingers.wsapp.request.FacebookLoginRequest;
import com.weddingsingers.wsapp.request.LoginRequest;
import com.weddingsingers.wsapp.request.ProfileRequest;

public class SplashActivity extends AppCompatActivity {

    int id;
    int type;
    String email;
    String name;
    String photoURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ProfileRequest profileRequest = new ProfileRequest(this);
        NetworkManager.getInstance().getNetworkData(profileRequest, new NetworkManager.OnResultListener<NetworkResult<User>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                Log.i("SplashActivity","profileRequest success");

                id = result.getResult().getId();
                type = result.getResult().getType();
                email = result.getResult().getEmail();
                name = result.getResult().getName();
                photoURL = result.getResult().getPhotoURL();

                moveMainActivity();

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                Log.i("SplashActivity","profileRequest fail");
                loginSharedPreference();
            }
        });

    }


    private void moveMainActivity() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                if(!TextUtils.isEmpty(email)) {
                    intent.putExtra(MainActivity.EXTRA_USER_ID, id);
                    intent.putExtra(MainActivity.EXTRA_USER_TYPE, type);
                    intent.putExtra(MainActivity.EXTRA_USER_NAME, name);
                    intent.putExtra(MainActivity.EXTRA_USER_EMAIL, email);
                    intent.putExtra(MainActivity.FRAG_NAME, MainActivity.FRAG_MAIN);
                }
                startActivity(intent);
                finish();
            }
        }, 500);
    }

    Handler mHandler = new Handler(Looper.getMainLooper());

    private void loginSharedPreference() {
        if (isFacebookLogin()) {
            processFacebookLogin();
        } else if (isAutoLogin()) {
            processAutoLogin();
        } else {
            moveMainActivity();
        }
    }

    private void facebookLogin() {
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult result) {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (!accessToken.getUserId().equals(PropertyManager.getInstance().getFacebookId())) {
                    resetFacebookAndMoveMainActivity();
                    return;
                }
                FacebookLoginRequest facebookLoginRequest = new FacebookLoginRequest(SplashActivity.this, accessToken.getToken(),
                        PropertyManager.getInstance().getRegistrationId());

                NetworkManager.getInstance().getNetworkData(facebookLoginRequest, new NetworkManager.OnResultListener<NetworkResult<User>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                        if (result.getCode() == 1) {
                            moveMainActivity();
                        } else {
                            resetFacebookAndMoveMainActivity();
                        }
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                        loginManager.logOut();
                        facebookLogin();
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
        loginManager.logInWithReadPermissions(this, null);
    }

    private void resetFacebookAndMoveMainActivity() {
        loginManager.logOut();
        PropertyManager.getInstance().setFacebookId("");
        moveMainActivity();
    }

    LoginManager loginManager;
    CallbackManager callbackManager;

    private void processFacebookLogin() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (!accessToken.getUserId().equals(PropertyManager.getInstance().getFacebookId())) {
            resetFacebookAndMoveMainActivity();
            return;
        }
        if (accessToken != null) {
            String token = accessToken.getToken();
            String regId = PropertyManager.getInstance().getRegistrationId();
            FacebookLoginRequest facebookLoginRequest = new FacebookLoginRequest(this, token, regId );
            NetworkManager.getInstance().getNetworkData(facebookLoginRequest, new NetworkManager.OnResultListener<NetworkResult<User>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                    if (result.getCode() == 1) {
                        moveMainActivity();
                    } else {
                        resetFacebookAndMoveMainActivity();
                    }
                }

                @Override
                public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                    loginManager.logOut();
                    facebookLogin();
                }
            });
        } else {
            facebookLogin();
        }
    }

    private boolean isFacebookLogin() {
        if (!TextUtils.isEmpty(PropertyManager.getInstance().getFacebookId())) {
            return true;
        }
        return false;
    }

    private boolean isAutoLogin() {
        String email = PropertyManager.getInstance().getEmail();
        Log.i("SplashActivity","email : " + email);
        return !TextUtils.isEmpty(email);
    }

    private void processAutoLogin(){
        String email = PropertyManager.getInstance().getEmail();
        if(!TextUtils.isEmpty(email)){
            String password = PropertyManager.getInstance().getPassword();
            String regId = PropertyManager.getInstance().getRegistrationId();
            LoginRequest loginRequest = new LoginRequest(this, email, password, regId);
            NetworkManager.getInstance().getNetworkData(loginRequest, new NetworkManager.OnResultListener<NetworkResult<User>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                    Toast.makeText(SplashActivity.this,"AutoLogin success",Toast.LENGTH_SHORT).show();
                    moveMainActivity();
                }

                @Override
                public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(SplashActivity.this,"AutoLogin fail",Toast.LENGTH_SHORT).show();
                    moveMainActivity();
                }
            });
        }
    }
}