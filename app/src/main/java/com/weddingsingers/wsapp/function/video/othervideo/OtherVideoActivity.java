package com.weddingsingers.wsapp.function.video.othervideo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.video.singerinfo.SingerInfoFragment;
import com.weddingsingers.wsapp.main.home.VideoListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherVideoActivity extends AppCompatActivity {

    @BindView(R.id.other_video_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_video);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        VideoListFragment videoListFragment = new VideoListFragment();
        ft.add(R.id.act_other_video_fl_container, videoListFragment);
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
