package com.weddingsingers.wsapp.function.schedulemgm.schedulemgm;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayOffActivity extends AppCompatActivity {

    @BindView(R.id.day_off_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_off);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        DayOffFragment dayOffFragment = new DayOffFragment();
        ft.add(R.id.act_day_off_fl_container,dayOffFragment);
        ft.commit();

    }
}
