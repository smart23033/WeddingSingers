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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.login.LoginActivity;
import com.weddingsingers.wsapp.main.alarm.AlarmFragment;
import com.weddingsingers.wsapp.main.chatting.ChattingListFragment;
import com.weddingsingers.wsapp.main.community.PostListFragment;
import com.weddingsingers.wsapp.main.home.MainFragment;
import com.weddingsingers.wsapp.main.mypage.MyPageCustomerFragment;
import com.weddingsingers.wsapp.main.mypage.MyPageSingerFragment;
import com.weddingsingers.wsapp.main.qna.QNAFragment;
import com.weddingsingers.wsapp.main.reservationmgm.ReservationMgmFragment;
import com.weddingsingers.wsapp.main.reservationmgm.ReservedCustomerFragment;
import com.weddingsingers.wsapp.main.review.ReviewFragment;
import com.weddingsingers.wsapp.main.schedulemgm.ScheduleMgmFragment;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.ProfileRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int DEFAULT_VALUE = 0;

    public static final int MESSAGE_BACK_KEY_TIMEOUT = 1;
    public static final int TIMEOUT_TIME = 2000;

    public static final String EXTRA_USER_TYPE = "userType";
    public static final String EXTRA_USER_ID = "userId";
    public static final String EXTRA_USER_EMAIL = "userEmail";
    public static final String EXTRA_USER_NAME = "userName";

    public static final int TYPE_SINGER = 1;
    public static final int TYPE_CUSTOMER = 2;

    public static final String FRAG_NAME = "fragmentName";

    public static final int FRAG_MAIN = 100;
    public static final int FRAG_MY_PAGE = 200;
    public static final int FRAG_RESERVATION_MGM = 300;
    public static final int FRAG_RESERVED_CUSTOMER = 310;
    public static final int FRAG_SCHEDULE_MGM = 400;
    public static final int FRAG_REVIEW = 500;
    public static final int FRAG_CHATTING = 600;
    public static final int FRAG_COMMUNITY = 700;
    public static final int FRAG_QNA = 800;
    public static final int FRAG_ALARM = 900;
    public static final int FRAG_VIDEO = 1000;

    public static Boolean IS_PICTURE_CHANGED = false;

    int userId;
    int userType;
    String userName;
    String userEmail;
    String photoURL;

    @BindView(R.id.main_drawer)
    DrawerLayout drawer;

    @BindView(R.id.main_nv_nav)
    NavigationView naviView;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_tv_toolbar)
    TextView titleTextView;

    View headerView;

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
        headerView = naviView.inflateHeaderView(R.layout.nav_main_header);

        if (savedInstanceState == null) {
            changeFragment(new MainFragment());
            titleTextView.setText(getResources().getString(R.string.app_name));
        }

        Intent intent = getIntent();
        isLogin(intent);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        int fragmentName = intent.getIntExtra(FRAG_NAME, DEFAULT_VALUE);
        Log.i("MainActivity", "fragName : " + fragmentName);

        changeFragmentFromAnotherActivity(fragmentName);
    }

    //다른 액티비티안의 프레그먼트에서 메인액티비티의 프레그먼트를 변경하도록 요청할 때
    private void changeFragmentFromAnotherActivity(int fragmentName) {
        if (fragmentName != DEFAULT_VALUE) {
            switch (fragmentName) {
                case FRAG_RESERVATION_MGM: {
                    changeFragment(new ReservationMgmFragment());
                    titleTextView.setText(getResources().getString(R.string.nav_reservation_mgm));
                    break;
                }
                case FRAG_RESERVED_CUSTOMER: {
                    changeFragment(new ReservedCustomerFragment());
                    titleTextView.setText(getResources().getString(R.string.nav_reservation_mgm));
                    break;
                }
                case FRAG_MY_PAGE: {
                    if(userType == TYPE_SINGER) {
                        changeFragment(new MyPageSingerFragment());
                    }else if(userType == TYPE_CUSTOMER){
                        changeFragment(new MyPageCustomerFragment());
                    }
                    titleTextView.setText(getResources().getString(R.string.nav_my_page));
                    break;
                }
                case FRAG_SCHEDULE_MGM: {
                    changeFragment(ScheduleMgmFragment.newInstance(userId));
                    titleTextView.setText(getResources().getString(R.string.nav_schedule_mgm));
                    break;
                }
                case FRAG_MAIN : {
                    changeFragment(new MainFragment());
                    titleTextView.setText(getResources().getString(R.string.nav_home));
                    break;
                }
                case FRAG_VIDEO : {
//                    아직
                    break;
                }
                default:
                    break;
            }
        }
    }

    RoundedImageView pictureView;
    ImageButton alarmBtn;
    TextView nameView;
    TextView emailView;
    Button loginBtn;

    //로그아웃상태의 네비게이션 드로워, 인자 없으면 로그아웃 상태임.
    private void isLogin(Intent intent) {
        naviView.getMenu().clear();

        pictureView = (RoundedImageView) headerView.findViewById(R.id.nav_header_picture);
        alarmBtn = (ImageButton) headerView.findViewById(R.id.nav_header_bell);
        nameView = (TextView) headerView.findViewById(R.id.nav_header_name);
        emailView = (TextView) headerView.findViewById(R.id.nav_header_email);
        loginBtn = (Button) findViewById(R.id.nav_btn_login);

        pictureView.mutateBackground(true);
        pictureView.setOval(true);

        int fragmentName = intent.getIntExtra(FRAG_NAME, DEFAULT_VALUE);

        if (fragmentName != DEFAULT_VALUE) {

            IS_PICTURE_CHANGED = true;

            userId = intent.getIntExtra(EXTRA_USER_ID, DEFAULT_VALUE);
            userType = intent.getIntExtra(EXTRA_USER_TYPE, DEFAULT_VALUE);
            userName = intent.getStringExtra(EXTRA_USER_NAME);
            userEmail = intent.getStringExtra(EXTRA_USER_EMAIL);

            nameView.setText(userName);
            emailView.setText(userEmail);
            Glide.with(this)
                    .load(photoURL)
                    .asBitmap()
                    .centerCrop()
                    .error(ContextCompat.getDrawable(this, R.drawable.login_ic_01))
                    .into(pictureView);

            loginBtn.setVisibility(View.GONE);

//            싱어일 때 네비게이션 드로워
            if (userType == TYPE_SINGER) {
                naviView.inflateMenu(R.menu.main_drawer_singer);
            } else {
//            고객일 때 네비게이션 드로워
                naviView.inflateMenu(R.menu.main_drawer_customer);
            }

            alarmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeNavMenu(AlarmFragment.newInstance(userType));
                }
            });
        } else { //로그아웃 상태

            alarmBtn.setVisibility(View.INVISIBLE);
            pictureView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.login_ic_01));
            nameView.setVisibility(View.INVISIBLE);
            emailView.setVisibility(View.INVISIBLE);

            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });

        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawer = (DrawerLayout) findViewById(R.id.main_drawer);

        switch (item.getItemId()) {
            case R.id.nav_home: {
                changeNavMenu(new MainFragment());
                titleTextView.setText(getResources().getString(R.string.app_name));
                return true;
            }
            case R.id.nav_my_page: {
                if (userType == TYPE_CUSTOMER) { // 수요자 TYPE_CUSTOMER /공급자 TYPE_SINGER
                    changeNavMenu(new MyPageCustomerFragment());
                } else {
                    changeNavMenu(new MyPageSingerFragment());
                }
                titleTextView.setText(getResources().getString(R.string.nav_my_page));
                return true;
            }
            case R.id.nav_reservation_mgm: {
//               수요자의 예약관리
                if(userType == TYPE_CUSTOMER) {
                    changeNavMenu(new ReservationMgmFragment());
                }
//                공급자의 예약관리
                else if (userType == TYPE_SINGER) {
                    changeNavMenu(new ReservedCustomerFragment());
                }
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
                changeNavMenu(ScheduleMgmFragment.newInstance(userId));
                titleTextView.setText(getResources().getString(R.string.nav_schedule_mgm));
                return true;
            }
        }
        return true;
    }

    private void changeNavMenu(Fragment f) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.act_main_fl_container, f)
                .commit();
        drawer.closeDrawer(GravityCompat.START);
    }

    public void changeFragment(Fragment f) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.act_main_fl_container, f)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                if (IS_PICTURE_CHANGED) {
                    setPictureView();
                }
                drawer.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 프로필 이미지 다시 불러오기(마이페이지에서 사진 변경)
    public void setPictureView() {

        ProfileRequest request = new ProfileRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {

                User user = new User();
                user.setPhotoURL(result.getResult().getPhotoURL());

                Glide.with(MainActivity.this)
                        .load(user.getPhotoURL())
                        .asBitmap()
                        .centerCrop()
                        .error(ContextCompat.getDrawable(MainActivity.this, R.drawable.login_ic_01))
                        .into(pictureView);
                IS_PICTURE_CHANGED = false;

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(MainActivity.this, "UserInfoFragment failure", Toast.LENGTH_SHORT).show();

            }
        });
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
