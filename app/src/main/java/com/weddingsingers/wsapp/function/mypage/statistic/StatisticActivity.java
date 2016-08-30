package com.weddingsingers.wsapp.function.mypage.statistic;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.mypage.myinquiry.MyInquiryFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticActivity extends AppCompatActivity {

    @BindView(R.id.statistic_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        StatisticFragment statisticFragment = new StatisticFragment();
        ft.add(R.id.act_statistic_fl_container, statisticFragment);
        ft.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

