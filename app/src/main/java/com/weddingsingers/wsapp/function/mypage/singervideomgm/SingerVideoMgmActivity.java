package com.weddingsingers.wsapp.function.mypage.singervideomgm;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.mypage.mypage.UserInfoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingerVideoMgmActivity extends AppCompatActivity {

    @BindView(R.id.singer_video_mgm_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer_video_mgm);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        SingerVideoMgmFragment singerVideoMgmFragment = new SingerVideoMgmFragment();
        ft.add(R.id.act_singer_video_mgm_fl_container, singerVideoMgmFragment);
        ft.commit();
    }

}
