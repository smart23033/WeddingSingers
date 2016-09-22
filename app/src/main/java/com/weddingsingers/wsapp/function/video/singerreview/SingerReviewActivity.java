package com.weddingsingers.wsapp.function.video.singerreview;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.video.othervideo.OtherVideoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingerReviewActivity extends AppCompatActivity {

    public static final String EXTRA_SINGER_ID = "singerId";
    public static final int DEFAULT_VALUE = 0;


    @BindView(R.id.singer_review_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer_review);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        int singerId = intent.getIntExtra(EXTRA_SINGER_ID, DEFAULT_VALUE);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        SingerReviewFragment singerReviewFragment = SingerReviewFragment.newInstance(singerId);
        ft.replace(R.id.act_singer_review_fl_container, singerReviewFragment);
        ft.commit();
    }
}
