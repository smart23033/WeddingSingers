package com.weddingsingers.wsapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.fcm.MyFirebaseInstanceIDService;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.manager.PropertyManager;
import com.weddingsingers.wsapp.request.FacebookLoginRequest;
import com.weddingsingers.wsapp.request.LoginRequest;
import com.weddingsingers.wsapp.request.ProfileRequest;

public class SplashActivity extends AppCompatActivity {

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    LoginManager loginManager;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i("SplashActivity","onReceive doRealStart");
                doRealStart();
            }
        };
        setUpIfNeeded();

    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(MyFirebaseInstanceIDService.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    private void setUpIfNeeded() {
        if (checkPlayServices()) {
            String regId = PropertyManager.getInstance().getRegistrationId();
            Log.i("SplashActivity","setUpIfNeeded regId : " + regId);
            if (!regId.equals("")) {
                Log.i("SplashActivity","setUpIfNeeded doRealStart");
                doRealStart();
            } else {
                Log.i("SplashActivity","setUpIfNeeded startService");
                Intent intent = new Intent(this, MyFirebaseInstanceIDService.class);
                startService(intent);
            }
        }
    }

    private void doRealStart(){
        ProfileRequest profileRequest = new ProfileRequest(this);
        NetworkManager.getInstance().getNetworkData(profileRequest, new NetworkManager.OnResultListener<NetworkResult<User>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                Log.i("SplashActivity", "profileRequest success");

                int id = result.getResult().getId();
                int type = result.getResult().getType();
                String email = result.getResult().getEmail();
                String name = result.getResult().getName();
                String photoURL = result.getResult().getPhotoURL();

                moveMainActivity(id, email, name, type);

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                Log.i("SplashActivity", "profileRequest fail");
                loginSharedPreference();
            }
        });
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                Dialog dialog = apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                });
                dialog.show();
            } else {
                finish();
            }
            return false;
        }
        return true;
    }

    private void moveMainActivity() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 500);
    }


    private void moveMainActivity(final int id, final String email, final String name, final int type) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);

                intent.putExtra(MainActivity.EXTRA_USER_ID, id);
                intent.putExtra(MainActivity.EXTRA_USER_TYPE, type);
                intent.putExtra(MainActivity.EXTRA_USER_NAME, name);
                intent.putExtra(MainActivity.EXTRA_USER_EMAIL, email);
                intent.putExtra(MainActivity.FRAG_NAME, MainActivity.FRAG_MAIN);
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
                        int id = result.getResult().getId();
                        int type = result.getResult().getType();
                        String email = result.getResult().getEmail();
                        String name = result.getResult().getName();

                        moveMainActivity(id, email, name, type);
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

    private void processFacebookLogin() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (!accessToken.getUserId().equals(PropertyManager.getInstance().getFacebookId())) {
            resetFacebookAndMoveMainActivity();
            return;
        }
        if (accessToken != null) {
            String token = accessToken.getToken();
            String regId = PropertyManager.getInstance().getRegistrationId();
            FacebookLoginRequest facebookLoginRequest = new FacebookLoginRequest(this, token, regId);
            NetworkManager.getInstance().getNetworkData(facebookLoginRequest, new NetworkManager.OnResultListener<NetworkResult<User>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                        int id = result.getResult().getId();
                        int type = result.getResult().getType();
                        String email = result.getResult().getEmail();
                        String name = result.getResult().getName();

                        moveMainActivity(id, email, name, type);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLAY_SERVICES_RESOLUTION_REQUEST &&
                resultCode == Activity.RESULT_OK) {
            setUpIfNeeded();
            return;
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private boolean isFacebookLogin() {
        if (!TextUtils.isEmpty(PropertyManager.getInstance().getFacebookId())) {
            return true;
        }
        return false;
    }

    private boolean isAutoLogin() {
        String email = PropertyManager.getInstance().getEmail();
        return !TextUtils.isEmpty(email);
    }

    private void processAutoLogin() {
        String email = PropertyManager.getInstance().getEmail();
        if (!TextUtils.isEmpty(email)) {
            String password = PropertyManager.getInstance().getPassword();
            String regId = PropertyManager.getInstance().getRegistrationId();
            LoginRequest loginRequest = new LoginRequest(this, email, password, regId);
            NetworkManager.getInstance().getNetworkData(loginRequest, new NetworkManager.OnResultListener<NetworkResult<User>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                    Toast.makeText(SplashActivity.this, "AutoLogin success", Toast.LENGTH_SHORT).show();

                    int id = result.getResult().getId();
                    int type = result.getResult().getType();
                    String email = result.getResult().getEmail();
                    String name = result.getResult().getName();

                    moveMainActivity(id, email, name, type);
                }

                @Override
                public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(SplashActivity.this, "AutoLogin fail", Toast.LENGTH_SHORT).show();
                    moveMainActivity();
                }
            });
        }
    }
}