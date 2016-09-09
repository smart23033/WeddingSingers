package com.weddingsingers.wsapp.function.video.video;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.BinderThread;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SearchResult;
import com.weddingsingers.wsapp.data.Video;
import com.weddingsingers.wsapp.function.search.search.RecentSearchFragment;
import com.weddingsingers.wsapp.function.search.search.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity {
    public static final String EXTRA_VIDEO_ID = "videoId";
    public static final String EXTRA_SINGER_ID = "singerId";

    public static final int DEFAULT_VALUE = 0;

    @BindView(R.id.video_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        int videoId = intent.getIntExtra(EXTRA_VIDEO_ID, DEFAULT_VALUE);
        int singerId = intent.getIntExtra(EXTRA_SINGER_ID, DEFAULT_VALUE);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        VideoFragment videoFragment = VideoFragment.newInstance(videoId, singerId);
        ft.add(R.id.act_video_fl_container,videoFragment);
        ft.commit();


    }

}
