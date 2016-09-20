package com.weddingsingers.wsapp.function.video.video;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.BinderThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.SearchResult;
import com.weddingsingers.wsapp.data.Video;
import com.weddingsingers.wsapp.function.search.search.RecentSearchFragment;
import com.weddingsingers.wsapp.function.search.search.SearchActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.VideoRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {
    public static final String EXTRA_VIDEO_ID = "videoId";
    public static final String EXTRA_SINGER_ID = "singerId";


    private static final String DEVELOPER_KEY = "AIzaSyBE7cGgtf2Dts693vD_JKXM0c_WDVtwg1M";
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    public static final int DEFAULT_VALUE = 0;

    @BindView(R.id.video_toolbar)
    Toolbar toolbar;

    YouTubePlayerSupportFragment youTubePlayerSupportFragment;

    String url;
    int videoId;
    int singerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        videoId = intent.getIntExtra(EXTRA_VIDEO_ID, DEFAULT_VALUE);
        singerId = intent.getIntExtra(EXTRA_SINGER_ID, DEFAULT_VALUE);

        youTubePlayerSupportFragment =
                (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.video_frag_player);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        VideoFragment videoFragment = VideoFragment.newInstance(videoId, singerId);
        ft.replace(R.id.act_video_fl_container,videoFragment);
        ft.commit();


    }

    @Override
    protected void onResume() {
        super.onResume();

        VideoRequest videoRequest = new VideoRequest(this, videoId);
        NetworkManager.getInstance().getNetworkData(videoRequest, new NetworkManager.OnResultListener<NetworkResult<Video>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Video>> request, NetworkResult<Video> result) {

                url = result.getResult().getUrl();

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Video>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(VideoActivity.this, "videoRequest fail", Toast.LENGTH_SHORT).show();
            }
        });

        youTubePlayerSupportFragment.initialize(DEVELOPER_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            //I assume the below String value is your video id
            youTubePlayer.cueVideo("zQhZUGNR6l4");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
        }
    }
}
