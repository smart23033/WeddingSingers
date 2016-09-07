package com.weddingsingers.wsapp.function.mypage.mypage;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingerProfileModifyActivity extends AppCompatActivity {

    @BindView(R.id.singer_video_profile_modify_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer_profile_modify);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        SingerProfileModifyFragment singerProfileModifyFragment = new SingerProfileModifyFragment();
        ft.add(R.id.act_singer_video_profile_modify_fl_container, singerProfileModifyFragment);
        ft.commit();
    }
}
