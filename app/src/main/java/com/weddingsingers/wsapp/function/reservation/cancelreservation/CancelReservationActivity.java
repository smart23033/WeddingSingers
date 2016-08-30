package com.weddingsingers.wsapp.function.reservation.cancelreservation;

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


        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        CancelReservationFragment cancelReservationFragment = new CancelReservationFragment();
        ft.add(R.id.act_cancel_reservation_fl_container,cancelReservationFragment);
        ft.commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Toast.makeText(CancelReservationActivity.this, "CancelReservationActivity's HomeAsUp Clicked", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    MenuItem searchMenuItem;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        searchMenuItem = menu.findItem(R.id.main_menu_search);
        searchMenuItem.setVisible(false);
        return true;
    }

}
