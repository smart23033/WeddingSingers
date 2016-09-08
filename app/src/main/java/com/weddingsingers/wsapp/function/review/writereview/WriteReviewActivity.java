package com.weddingsingers.wsapp.function.review.writereview;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.video.singerinfo.SingerInfoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteReviewActivity extends AppCompatActivity {

    @BindView(R.id.write_review_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        WriteReviewFragment writeReviewFragment = new WriteReviewFragment();
        ft.add(R.id.act_review_fl_container, writeReviewFragment);
        ft.commit();

    }
}
