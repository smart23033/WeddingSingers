package com.weddingsingers.wsapp.login;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.weddingsingers.wsapp.R;

import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

//    Toolbar toolbar;
//    TabLayout tabs;
//    ViewPager pager;
//    LoginPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        toolbar = (Toolbar)findViewById(R.id.login_toolbar);
//        tabs = (TabLayout)findViewById(R.id.login_tabs);
//        pager = (ViewPager)findViewById(R.id.login_pager);
//        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        tabs.addTab(tabs.newTab().setText("Login"));
//        tabs.addTab(tabs.newTab().setText("Sign Up"));
//
//        mAdapter = new LoginPagerAdapter(getSupportFragmentManager(),tabs.getTabCount());
//        pager.setAdapter(mAdapter);
//        tabs.setupWithViewPager(pager);

        ButterKnife.bind(this);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        LoginIntroFragment loginIntroFragment = new LoginIntroFragment();
        ft.add(R.id.act_login_container,loginIntroFragment);
        ft.commit();


    }

}