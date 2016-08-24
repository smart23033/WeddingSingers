package com.weddingsingers.wsapp.login;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.weddingsingers.wsapp.R;

public class LoginActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar)findViewById(R.id.login_toolbar);
        tabs = (TabLayout)findViewById(R.id.login_tabs);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();

        SignUpFragment f = new SignUpFragment();

        ft.add(R.id.login_tabs_container,f);
        ft.commit();
    }

}
