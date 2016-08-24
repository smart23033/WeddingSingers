package com.weddingsingers.wsapp.main;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.main.chatting.ChattingListFragment;
import com.weddingsingers.wsapp.main.community.PostListFragment;
import com.weddingsingers.wsapp.main.home.MainFragment;
import com.weddingsingers.wsapp.main.mypage.MyPageFragment;
import com.weddingsingers.wsapp.main.qna.QNAFragment;
import com.weddingsingers.wsapp.main.reservationmgm.ReservationListFragment;
import com.weddingsingers.wsapp.main.review.ReviewFragment;
import com.weddingsingers.wsapp.main.schedulemgm.ScheduleMgmFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView naviView;

    @BindView(R.id.text_toolbar_title)
    TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        drawer = (DrawerLayout)findViewById(R.id.drawer);
        naviView = (NavigationView)findViewById(R.id.navi_menu);
        naviView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_icon);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_fl_container, MainFragment.getInstance())
                    .commit();
            titleTextView.setText(getResources().getString(R.string.app_name));
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fl_container, MainFragment.getInstance())
                    .commit();
            titleTextView.setText(getResources().getString(R.string.app_name));
        } else if (id == R.id.nav_mypage) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fl_container, MyPageFragment.getInstance())
                    .commit();
            titleTextView.setText(getResources().getString(R.string.nav_mypage));
        } else if (id == R.id.nav_reservation_mgm) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fl_container, ReservationListFragment.getInstance())
                    .commit();
            titleTextView.setText(getResources().getString(R.string.nav_reservation_mgm));
        } else if (id == R.id.nav_review) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fl_container, ReviewFragment.getInstance())
                    .commit();
            titleTextView.setText(getResources().getString(R.string.nav_review));
        } else if (id == R.id.nav_chat) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fl_container, ChattingListFragment.getInstance())
                    .commit();
            titleTextView.setText(getResources().getString(R.string.nav_chat));
        } else if (id == R.id.nav_community) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fl_container, PostListFragment.getInstance())
                    .commit();
            titleTextView.setText(getResources().getString(R.string.nav_community));
        } else if (id == R.id.nav_qna) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fl_container, QNAFragment.getInstance())
                    .commit();
            titleTextView.setText(getResources().getString(R.string.nav_qna));
        } else if (id == R.id.nav_schedule_mgm) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fl_container, ScheduleMgmFragment.getInstance())
                    .commit();
            titleTextView.setText(getResources().getString(R.string.nav_schedule_mgm));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(Fragment f) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fl_container, f)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            if(drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            }else{
                drawer.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
