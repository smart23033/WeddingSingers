package com.weddingsingers.wsapp.function.schedulemgm.schedulemgm;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailScheduleActivity extends AppCompatActivity {

    public final static String EXTRA_YEAR = "extraYear";
    public final static String EXTRA_MONTH = "extraMonth";

    public final static int DEFAULT_VALUE = 0;

    @BindView(R.id.detail_schedule_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_schedule);


        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Intent intent = getIntent();
        int year = intent.getIntExtra(EXTRA_YEAR, DEFAULT_VALUE);
        int month = intent.getIntExtra(EXTRA_MONTH, DEFAULT_VALUE);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        DetailScheduleFragment detailScheduleFragment = DetailScheduleFragment.newInstance(year, month);
        ft.add(R.id.act_detail_schedule_fl_container,detailScheduleFragment);
        ft.commit();

    }

}
