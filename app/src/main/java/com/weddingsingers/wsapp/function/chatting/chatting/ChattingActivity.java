package com.weddingsingers.wsapp.function.chatting.chatting;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.main.home.VideoListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChattingActivity extends AppCompatActivity {

    @BindView(R.id.chatting_toolbar)
    Toolbar toolbar;

    public static final String EXTRA_USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Bundle bundle = new Bundle();
        bundle.putString("EXTRA_USER", EXTRA_USER);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        ChattingFragment chattingFragment = new ChattingFragment();
        chattingFragment.setArguments(bundle);
        ft.add(R.id.act_chatting_fl_container, chattingFragment);
        ft.commit();
    }

}