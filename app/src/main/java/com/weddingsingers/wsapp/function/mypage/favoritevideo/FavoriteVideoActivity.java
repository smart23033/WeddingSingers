package com.weddingsingers.wsapp.function.mypage.favoritevideo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.mypage.mypoint.MyPointFragment;
import com.weddingsingers.wsapp.main.home.VideoListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteVideoActivity extends AppCompatActivity {

    @BindView(R.id.favorite_video_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_video);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        FavoriteVideoFragment favoriteVideoFragment = new FavoriteVideoFragment();
        ft.add(R.id.act_favorite_video_fl_container, favoriteVideoFragment);
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
