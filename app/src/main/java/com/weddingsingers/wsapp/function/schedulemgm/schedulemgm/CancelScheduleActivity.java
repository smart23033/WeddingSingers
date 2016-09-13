package com.weddingsingers.wsapp.function.schedulemgm.schedulemgm;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CancelScheduleActivity extends AppCompatActivity {

    public final static String FRAG_NAME = "fragmentName";
    public static final String EXTRA_ESTIMATE_ID = "estimateId";
    public static final int DEFAULT_VALUE = 0;

    @BindView(R.id.cancel_schedule_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_schedule);


        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        int estimateId = intent.getIntExtra(EXTRA_ESTIMATE_ID, DEFAULT_VALUE);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        CancelScheduleFragment cancelScheduleFragment = CancelScheduleFragment.newInstance(estimateId);
        ft.add(R.id.act_cancel_schedule_fl_container,cancelScheduleFragment);
        ft.commit();



    }
}
