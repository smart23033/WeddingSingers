package com.weddingsingers.wsapp.function.video.reservation;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReservationActivity extends AppCompatActivity {

    public final static String EXTRA_SINGER_ID = "singerId";


    @BindView(R.id.reservation_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        int singerId = intent.getIntExtra(EXTRA_SINGER_ID, 0);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        ReservationFragment reservationFragment = ReservationFragment.newInstance(singerId);
        ft.replace(R.id.act_reservation_fl_container,reservationFragment);
        ft.commit();
    }

}
