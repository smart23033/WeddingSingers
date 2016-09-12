package com.weddingsingers.wsapp.function.schedulemgm.schedulemgm;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayOffActivity extends AppCompatActivity {

    public static final String EXTRA_SINGER_ID = "singerId";
    public static final int DEFAULT_VALUE = 0;

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


        Intent intent = getIntent();
        int singerId = intent.getIntExtra(EXTRA_SINGER_ID, DEFAULT_VALUE);

        Log.i("DayOffActivity","singerId : " + singerId);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        DayOffFragment dayOffFragment =DayOffFragment.newInstance(singerId);
        ft.add(R.id.act_day_off_fl_container,dayOffFragment);
        ft.commit();

    }
}
