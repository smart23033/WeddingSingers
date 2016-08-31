package com.weddingsingers.wsapp.function.schedulemgm.schedulemgm;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailScheduleActivity extends AppCompatActivity {

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



        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        DetailScheduleFragment detailScheduleFragment = new DetailScheduleFragment();
        ft.add(R.id.act_detail_schedule_fl_container,detailScheduleFragment);
        ft.commit();

    }

}
