package com.weddingsingers.wsapp.function.search.search;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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

    @BindView(R.id.search_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        RecentSearchFragment f = new RecentSearchFragment();
        ft.add(R.id.act_search_fl_container,f);
        ft.commit();

//        두번째 클릭 부터 반응??
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!filterBtn.isChecked()) {
                    FragmentTransaction ft = getSupportFragmentManager()
                            .beginTransaction();
                    FilterFragment filterFragment = new FilterFragment();
                    ft.replace(R.id.act_search_fl_container, filterFragment);
                    ft.commit();
                }else{
                    FragmentTransaction ft = getSupportFragmentManager()
                            .beginTransaction();
                    RecentSearchFragment recentSearchFragment = new RecentSearchFragment();
                    ft.replace(R.id.act_search_fl_container, recentSearchFragment);
                    ft.commit();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: {
                Toast.makeText(SearchActivity.this,"SearchActivity's HomeAsUp Clicked", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            }
             case R.id.main_menu_search: {
                 Toast.makeText(SearchActivity.this,"SearchActivity's Search Button Clicked", Toast.LENGTH_SHORT).show();

                 FragmentTransaction ft = getSupportFragmentManager()
                         .beginTransaction();
                 SearchResultFragment searchResultFragment = new SearchResultFragment();
                 ft.replace(R.id.act_search_fl_container, searchResultFragment);
                 ft.commit();

                 return true;
             }
         }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }


}
