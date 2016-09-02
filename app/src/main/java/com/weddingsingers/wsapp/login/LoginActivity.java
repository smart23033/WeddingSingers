package com.weddingsingers.wsapp.login;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

//    Toolbar toolbar;
//    TabLayout tabs;
//    ViewPager pager;
//    LoginPagerAdapter mAdapter;

    final static String FRAG_LOGIN_INTRO = "LoginIntroFragment";
    final static String FRAG_LOGIN = "LoginFragment";
    final static String FRAG_SIGN_UP_SECOND = "SignUpSecondFragment";

    @BindView(R.id.login_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//        deprecated
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

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        LoginIntroFragment loginIntroFragment = new LoginIntroFragment();
        ft.add(R.id.act_login_container, loginIntroFragment, FRAG_LOGIN_INTRO);
        ft.commit();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.act_login_container);
            String fragmentTag = currentFragment.getTag();
            boolean flag = false;
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);

            if (fragmentTag != FRAG_LOGIN_INTRO) {
                FragmentTransaction ft = getSupportFragmentManager()
                        .beginTransaction();
                ft.detach(currentFragment);
                ft.commit();

                if(fragmentTag == FRAG_LOGIN || fragmentTag == FRAG_SIGN_UP_SECOND) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            } else {
                finish();
            }


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}