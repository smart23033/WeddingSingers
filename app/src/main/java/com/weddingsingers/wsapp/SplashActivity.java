package com.weddingsingers.wsapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.manager.PropertyManager;
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

                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                intent.putExtra(MainActivity.EXTRA_USER_ID, id);
                intent.putExtra(MainActivity.EXTRA_USER_TYPE, type);
                intent.putExtra(MainActivity.EXTRA_USER_NAME, name);
                intent.putExtra(MainActivity.EXTRA_USER_EMAIL, email);
                intent.putExtra(MainActivity.FRAG_NAME, MainActivity.FRAG_MAIN);
                moveMainActivity(intent);

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                Log.i("SplashActivity","profileRequest fail");
                loginSharedPreference();
            }
        });

    }


    private void moveMainActivity(final Intent intent) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 500);
    }

    private void moveMainActivity() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        }, 500);
    }

    Handler mHandler = new Handler(Looper.getMainLooper());

    private void loginSharedPreference() {
        Log.i("SplashActivity","isAutoLogin : " + isAutoLogin());
        if (isAutoLogin()) {
            processAutoLogin();
        } else {
            moveMainActivity();
        }
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
            String regid = PropertyManager.getInstance().getRegistrationId();
            LoginRequest loginRequest = new LoginRequest(this, email, password, regid);
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
