package com.weddingsingers.wsapp.main;

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
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.main.home.MainFragment;
import com.weddingsingers.wsapp.main.mypage.MyPageFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView naviView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        } else if (id == R.id.nav_mypage) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fl_container, MyPageFragment.getInstance())
                    .commit();
        } else if (id == R.id.nav_reservation_mgm) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fl_container, MainFragment.getInstance())
                    .commit();
        } else if (id == R.id.nav_review) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fl_container, MainFragment.getInstance())
                    .commit();
        } else if (id == R.id.nav_chat) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fl_container, MainFragment.getInstance())
                    .commit();
        } else if (id == R.id.nav_community) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fl_container, MainFragment.getInstance())
                    .commit();
        } else if (id == R.id.nav_qna) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fl_container, MainFragment.getInstance())
                    .commit();
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
