package com.weddingsingers.wsapp.function.video.video;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SearchResult;

public class VideoActivity extends AppCompatActivity {
    public static final String EXTRA_SEARCH_RESULT = "searchResult";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Intent intent = getIntent();

        SearchResult searchResult = (SearchResult)intent.getSerializableExtra(EXTRA_SEARCH_RESULT);
        String text = searchResult.getTitle();

        Toast.makeText(VideoActivity.this,text,Toast.LENGTH_SHORT).show();
    }
}
