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
import android.text.TextUtils;
import android.util.Log;
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
import com.weddingsingers.wsapp.function.mypage.accountmgm.AccountMgmActivity;
import com.weddingsingers.wsapp.function.mypage.favoritevideo.FavoriteVideoActivity;
import com.weddingsingers.wsapp.function.mypage.myinquiry.MyInquiryActivity;
import com.weddingsingers.wsapp.function.mypage.pointstore.PointStoreActivity;
import com.weddingsingers.wsapp.function.mypage.statistic.StatisticActivity;
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
import com.weddingsingers.wsapp.main.reservationmgm.ReservedOneFragment;
import com.weddingsingers.wsapp.main.review.ReviewFragment;
import com.weddingsingers.wsapp.main.schedulemgm.ScheduleMgmFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int MESSAGE_BACK_KEY_TIMEOUT = 1;
    public static final int TIMEOUT_TIME = 2000;

    public static final int FRAG_MAIN = 100;
    public static final int FRAG_MY_PAGE = 200;
    public static final int FRAG_RESERVATION_MGM = 300;
    public static final int FRAG_SCHEDULE_MGM = 400;
    public static final int FRAG_REVIEW = 500;
    public static final int FRAG_CHATTING = 600;
    public static final int FRAG_COMMUNITY = 700;
    public static final int FRAG_QNA = 800;

    public static final int ERROR_CODE = -1;

    @BindView(R.id.drawer)
    DrawerLayout drawer;

    @BindView(R.id.navi_menu)
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

        naviView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            changeFragment(new MainFragment());
            titleTextView.setText(getResources().getString(R.string.app_name));
        }

        Intent intent = getIntent();
        int fragmentName = intent.getIntExtra("fragmentName", ERROR_CODE);
        changeFragmentFromAnotherActivity(fragmentName);

        boolean isLogin = intent.getBooleanExtra("login", false);
        checkLogin(isLogin);

    }

    //다른 액티비티안의 프레그먼트에서 메인액티비티의 프레그먼트를 변경하도록 요청할 때
    private void changeFragmentFromAnotherActivity(int fragmentName) {
        if (fragmentName != ERROR_CODE) {
            switch (fragmentName) {
                case FRAG_RESERVATION_MGM: {
                    changeFragment(new ReservationMgmFragment());
                    titleTextView.setText(getResources().getString(R.string.nav_reservation_mgm));
                    break;
                }
                default:
                    break;
            }
        }
    }

    //로그인 체크 후 네비게이션 드로워 변경
    private void checkLogin(boolean isLogin) {
        naviView.getMenu().clear();
        View headerView = naviView.inflateHeaderView(R.layout.nav_header_main);
        ImageButton alarmBtn = (ImageButton) headerView.findViewById(R.id.nav_header_bell);
        ImageView pictureBtn = (ImageView) headerView.findViewById(R.id.nav_header_picture);
        TextView nameTextView = (TextView) headerView.findViewById(R.id.nav_header_name);
        TextView emailTextView = (TextView) headerView.findViewById(R.id.nav_header_email);
        RelativeLayout navLayoutLogout = (RelativeLayout) findViewById(R.id.nav_layout_logout);

        if (isLogin) { // 로그인

            navLayoutLogout.setVisibility(View.GONE);

            naviView.inflateMenu(R.menu.activity_main_drawer_singer);

            alarmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeNavMenu(new AlarmFragment());
                }
            });

        } else { // 로그아웃

            Button navLoginBtn = (Button) findViewById(R.id.nav_login_btn);
            navLoginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });

            alarmBtn.setVisibility(View.INVISIBLE);
            pictureBtn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_nav_logout));
            nameTextView.setVisibility(View.INVISIBLE);
            emailTextView.setVisibility(View.INVISIBLE);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawer = (DrawerLayout) findViewById(R.id.drawer);

        switch (item.getItemId()) {
            case R.id.nav_home: {
                changeNavMenu(new MainFragment());
                titleTextView.setText(getResources().getString(R.string.app_name));
                searchMenuItem.setVisible(true);
                return true;
            }
            case R.id.nav_mypage: {
                if (false) {
                    changeNavMenu(new MyPageCustomerFragment());
                } else {
                    changeNavMenu(new MyPageSingerFragment());
                }
                titleTextView.setText(getResources().getString(R.string.nav_mypage));
                return true;
            }
            case R.id.nav_reservation_mgm: {
                changeNavMenu(new ReservationMgmFragment());
                titleTextView.setText(getResources().getString(R.string.nav_reservation_mgm));
                return true;
            }
            case R.id.nav_review: {
                changeNavMenu(new ReviewFragment());
                titleTextView.setText(getResources().getString(R.string.nav_review));
                return true;
            }
            case R.id.nav_chat: {
                changeNavMenu(new ChattingListFragment());
                titleTextView.setText(getResources().getString(R.string.nav_chat));
                return true;
            }
            case R.id.nav_community: {
                changeNavMenu(new PostListFragment());
                titleTextView.setText(getResources().getString(R.string.nav_community));
                return true;
            }
            case R.id.nav_qna: {
                changeNavMenu(new QNAFragment());
                titleTextView.setText(getResources().getString(R.string.nav_qna));
                return true;
            }
            case R.id.nav_schedule_mgm: {
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
        searchMenuItem.setVisible(false);

    }

    private void changeFragment(Fragment f) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fl_container, f)
                .commit();
    }

    MenuItem searchMenuItem;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        searchMenuItem = menu.findItem(R.id.main_menu_search);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
                return true;
            }
            case R.id.main_menu_search: {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!isBackPressed && !drawer.isDrawerOpen(GravityCompat.START)) {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.back_pressed), Toast.LENGTH_SHORT).show();
            isBackPressed = true;
            mHandler.sendEmptyMessageDelayed(MESSAGE_BACK_KEY_TIMEOUT, TIMEOUT_TIME);
        } else {
            mHandler.removeMessages(MESSAGE_BACK_KEY_TIMEOUT);
            finish();
        }
    }

}
