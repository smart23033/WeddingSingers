package com.weddingsingers.wsapp.function.reservation.cancelreservation;

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

public class CancelReservationActivity extends AppCompatActivity {
    public final static String FRAG_NAME = "fragmentName";
    public static final String EXTRA_ESTIMATE_ID = "estimateId";
    public static final int DEFAULT_VALUE = 0;

    @BindView(R.id.cancel_reservation_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Intent intent = getIntent();
        int estimateId = intent.getIntExtra(EXTRA_ESTIMATE_ID, DEFAULT_VALUE);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        CancelReservationFragment cancelReservationFragment = CancelReservationFragment.newInstance(estimateId);
        ft.add(R.id.act_cancel_reservation_fl_container,cancelReservationFragment);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
