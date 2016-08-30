package com.weddingsingers.wsapp.main;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.search.search.SearchActivity;
import com.weddingsingers.wsapp.login.LoginActivity;
import com.weddingsingers.wsapp.main.alarm.AlarmFragment;
import com.weddingsingers.wsapp.main.chatting.ChattingListFragment;
import com.weddingsingers.wsapp.main.community.PostListFragment;
import com.weddingsingers.wsapp.main.home.MainFragment;
import com.weddingsingers.wsapp.main.mypage.MyPageCustomerFragment;
import com.weddingsingers.wsapp.main.mypage.MyPageSingerFragment;
import com.weddingsingers.wsapp.main.qna.QNAFragment;
import com.weddingsingers.wsapp.main.reservationmgm.ReservationMgmFragment;
import com.weddingsingers.wsapp.main.review.ReviewFragment;
import com.weddingsingers.wsapp.main.schedulemgm.ScheduleMgmFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int MESSAGE_BACK_KEY_TIMEOUT = 1;
    public static final int TIMEOUT_TIME = 2000;

    DrawerLayout drawer;

    NavigationView naviView;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_tv_toolbar)
    TextView titleTextView;

    boolean isBackPressed = false;

    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_BACK_KEY_TIMEOUT:
                    isBackPressed = false;
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.navi_ic_hamburger);


        drawer = (DrawerLayout)findViewById(R.id.drawer);
        naviView = (NavigationView)findViewById(R.id.navi_menu);
        naviView.setNavigationItemSelectedListener(this);

        naviView.getMenu().clear();
        View headerView = naviView.inflateHeaderView(R.layout.nav_header_main);

        ImageButton alarmBtn = (ImageButton)headerView.findViewById(R.id.nav_header_bell);
        ImageView pictureBtn = (ImageView) headerView.findViewById(R.id.nav_header_picture);
        TextView nameTextView = (TextView) headerView.findViewById(R.id.nav_header_name);
        TextView emailTextView = (TextView) headerView.findViewById(R.id.nav_header_email);

        RelativeLayout navLayoutLogout = (RelativeLayout) findViewById(R.id.nav_layout_logout);

        Intent intent = getIntent();
        boolean isLogin = intent.getBooleanExtra("login", false);

        if(isLogin) { // 로그인

            navLayoutLogout.setVisibility(View.GONE);

            naviView.inflateMenu(R.menu.activity_main_drawer_singer);

            alarmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_fl_container, new AlarmFragment())
                            .commit();
                    drawer.closeDrawer(GravityCompat.START);
                }
            });

        } else { // 로그아웃

            Button navLoginBtn = (Button) findViewById(R.id.nav_login_btn);
            navLoginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            });

            alarmBtn.setVisibility(View.INVISIBLE);
            pictureBtn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_nav_logout));
            nameTextView.setVisibility(View.INVISIBLE);
            emailTextView.setVisibility(View.INVISIBLE);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_fl_container, new MainFragment())
                    .commit();
            titleTextView.setText(getResources().getString(R.string.app_name));
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawer = (DrawerLayout) findViewById(R.id.drawer);

        switch (item.getItemId()){
            case R.id.nav_home: {
                changeNavMenu(new MainFragment());
                titleTextView.setText(getResources().getString(R.string.app_name));
                return true;
            }
            case R.id.nav_mypage : {
                if (true) {
                    changeNavMenu(new MyPageCustomerFragment());
                } else {
                    changeNavMenu(new MyPageSingerFragment());
                }
                titleTextView.setText(getResources().getString(R.string.nav_mypage));
                return true;
            }
            case R.id.nav_reservation_mgm : {
                changeNavMenu(new ReservationMgmFragment());
                titleTextView.setText(getResources().getString(R.string.nav_reservation_mgm));
                return true;
            }
            case R.id.nav_review : {
                changeNavMenu(new ReviewFragment());
                titleTextView.setText(getResources().getString(R.string.nav_review));
                return true;
            }
            case R.id.nav_chat : {
                changeNavMenu(new ChattingListFragment());
                titleTextView.setText(getResources().getString(R.string.nav_chat));
                return true;
            }
            case R.id.nav_community : {
                changeNavMenu(new PostListFragment());
                titleTextView.setText(getResources().getString(R.string.nav_community));
                return  true;
            }
            case R.id.nav_qna : {
                changeNavMenu(new QNAFragment());
                titleTextView.setText(getResources().getString(R.string.nav_qna));
                return true;
            }
            case R.id.nav_schedule_mgm : {
                changeNavMenu(new ScheduleMgmFragment());
                titleTextView.setText(getResources().getString(R.string.nav_schedule_mgm));
                return true;
            }
        }
        return true;
    }

    private void changeNavMenu(Fragment f) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fl_container, f)
                .commit();
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: {
                if(drawer.isDrawerOpen(GravityCompat.START)){
                    drawer.closeDrawer(GravityCompat.START);
                }else{
                    drawer.openDrawer(GravityCompat.START);
                }
                return true;
            }
            case R.id.main_menu_search : {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(!isBackPressed && !drawer.isDrawerOpen(GravityCompat.START)) {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.back_pressed), Toast.LENGTH_SHORT).show();
            isBackPressed = true;
            mHandler.sendEmptyMessageDelayed(MESSAGE_BACK_KEY_TIMEOUT, TIMEOUT_TIME);
        } else {
            mHandler.removeMessages(MESSAGE_BACK_KEY_TIMEOUT);
            finish();
        }
    }
}
