package com.weddingsingers.wsapp.function.video.video;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.BinderThread;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SearchResult;
import com.weddingsingers.wsapp.function.search.search.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity {
    public static final String EXTRA_SEARCH_RESULT = "searchResult";

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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Toast.makeText(VideoActivity.this, "VideoActivity's HomeAsUp Clicked", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            }
            case R.id.video_menu_favorite: {
                if(!item.isChecked()){
                    item.setChecked(true);
                    item.setIcon(R.drawable.search_ic_favorite_on);
                }else{
                    item.setChecked(false);
                    item.setIcon(R.drawable.search_ic_favorite_off);
                }
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.video_menu,menu);
        return true;
    }


}
