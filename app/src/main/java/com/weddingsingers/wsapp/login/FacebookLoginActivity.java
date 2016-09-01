package com.weddingsingers.wsapp.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.weddingsingers.wsapp.R;

//서버 DB에 회원정보가 있는 경우 그냥 메인띄워주고, 없는경우 SIGN_UP_FIRST 페이지로 이동하라.
public class FacebookLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
    }
}
