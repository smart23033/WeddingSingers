package com.weddingsingers.wsapp.main.home;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TabLayout tabs;
    MyPagerAdapter mAdapter;
    ViewPager pager;
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

        pager = (ViewPager)findViewById(R.id.pager);
        tabs = (TabLayout)findViewById(R.id.tabs);

        tabs.addTab(tabs.newTab().setText("TAB1").setTag("tab1"));
        tabs.addTab(tabs.newTab().setText("TAB2").setTag("tab2"));
        tabs.addTab(tabs.newTab().setText("TAB3").setTag("tab3"));

        mAdapter = new MyPagerAdapter(getSupportFragmentManager(),tabs.getTabCount());

        pager.setAdapter(mAdapter);
        tabs.setupWithViewPager(pager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_icon);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Toast.makeText(MainActivity.this,"Home button clicked",Toast.LENGTH_SHORT).show();
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
