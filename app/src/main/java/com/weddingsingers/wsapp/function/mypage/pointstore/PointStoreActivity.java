package com.weddingsingers.wsapp.function.mypage.pointstore;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.mypage.myinquiry.MyInquiryFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PointStoreActivity extends AppCompatActivity {

    @BindView(R.id.point_store_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_store);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        PointStoreFragment pointStoreFragment = new PointStoreFragment();
        ft.add(R.id.act_point_store_fl_container, pointStoreFragment);
        ft.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
