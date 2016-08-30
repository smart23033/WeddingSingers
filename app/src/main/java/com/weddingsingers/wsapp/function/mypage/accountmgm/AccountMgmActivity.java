package com.weddingsingers.wsapp.function.mypage.accountmgm;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountMgmActivity extends AppCompatActivity {

    @BindView(R.id.account_mgm_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_mgm);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        AccountMgmFragment accountMgmFragment = new AccountMgmFragment();
        ft.add(R.id.act_account_mgm_fl_container, accountMgmFragment);
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
