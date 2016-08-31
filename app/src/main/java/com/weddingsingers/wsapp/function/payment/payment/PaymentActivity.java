package com.weddingsingers.wsapp.function.payment.payment;

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

public class PaymentActivity extends AppCompatActivity {
    public final static String RESULT_MESSAGE = "fragmentName";

    @BindView(R.id.payment_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        String fragmentName = intent.getStringExtra("fragmentName");

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        PaymentFragment paymentFragment = PaymentFragment.newInstance(fragmentName);
        ft.add(R.id.act_payment_fl_container,paymentFragment);
        ft.commit();
    }

}
