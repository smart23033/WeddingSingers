package com.weddingsingers.wsapp.function.search.search;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {


    @BindView(R.id.search_tv_keyword)
    EditText keywordView;

    @BindView(R.id.search_toggle_filter)
    ToggleButton filterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        RecentSearchFragment f = RecentSearchFragment.getInstance();
        ft.add(R.id.act_search_fl_container,f);
        ft.commit();

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager()
                        .beginTransaction();

                    if(!filterBtn.isChecked()) {
                        FilterFragment filterFragment = FilterFragment.getInstance();
                        ft.replace(R.id.act_search_fl_container, filterFragment);
                        ft.commit();
                    }else{
                       RecentSearchFragment recentSearchFragment = RecentSearchFragment.getInstance();
                        ft.replace(R.id.act_search_fl_container, recentSearchFragment);
                        ft.commit();
                    }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Toast.makeText(SearchActivity.this,"SearchActivity's HomeAsUp Clicked", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
