package com.weddingsingers.wsapp.function.chatting.chatting;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.function.video.video.VideoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChattingActivity extends AppCompatActivity {

    @BindView(R.id.chatting_toolbar)
    Toolbar toolbar;

    @BindView(R.id.chatting_toolbar_tv_title)
    TextView titleView;

    User user;

    public static final String EXTRA_USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        ButterKnife.bind(this);

        user = (User) getIntent().getSerializableExtra(EXTRA_USER);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Bundle bundle = new Bundle();
        bundle.putString("EXTRA_USER", EXTRA_USER);

        titleView.setText(user.getName());

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        ChattingFragment chattingFragment = ChattingFragment.newInstance(user);
        chattingFragment.setArguments(bundle);
        ft.replace(R.id.act_chatting_fl_container, chattingFragment);
        ft.commit();

        /*FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        ChattingFragment chattingFragment = new ChattingFragment();
        chattingFragment.setArguments(bundle);
        ft.replace(R.id.act_chatting_fl_container, chattingFragment);
        ft.commit();*/
    }

}