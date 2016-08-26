package com.weddingsingers.wsapp.function.search.search;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    final static String RECENT_SEARCH_FRAGMENT = "RecentSearchFragment";
    final static String FILTER_FRAGMENT = "FilterFragment";
    final static String SEARCH_RESULT_FRAGMENT = "SearchResultFragment";


    @BindView(R.id.search_et_keyword)
    EditText keywordView;

    @BindView(R.id.search_tv_keyword)
    TextView keywordTextView;

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
        RecentSearchFragment recentSearchFragment = new RecentSearchFragment();
        ft.add(R.id.act_search_fl_container,recentSearchFragment, RECENT_SEARCH_FRAGMENT);
        ft.commit();

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

                 Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.act_search_fl_container);

                if(currentFragment.getTag() != SEARCH_RESULT_FRAGMENT) {
//               searchResultFragment가 아닌 프레그먼트(filterFrag랑 RecentSearchFrag)에서 search버튼 누를 때
                    keywordTextView.setText(keywordView.getText());
                    keywordTextView.setVisibility(View.VISIBLE);
                    keywordView.setText("");
                    keywordView.setVisibility(View.GONE);
                    filterMenuItem.setVisible(false);

                    FragmentTransaction ft = getSupportFragmentManager()
                            .beginTransaction();
                    SearchResultFragment searchResultFragment = new SearchResultFragment();
                    ft.replace(R.id.act_search_fl_container, searchResultFragment , SEARCH_RESULT_FRAGMENT);
                    ft.commit();
                }else {
//               searchResultFragment에서 search버튼 누를 때
                    keywordTextView.setText("");
                    keywordTextView.setVisibility(View.GONE);
                    keywordView.setVisibility(View.VISIBLE);
                    filterMenuItem.setVisible(true);

                    FragmentTransaction ft = getSupportFragmentManager()
                            .beginTransaction();
                    RecentSearchFragment recentSearchFragment = new RecentSearchFragment();
                    ft.replace(R.id.act_search_fl_container, recentSearchFragment, RECENT_SEARCH_FRAGMENT);
                    ft.commit();
                }
                 return true;
             }
            case R.id.search_menu_filter:{
                if(!item.isChecked()){
                    item.setChecked(true);
                    item.setIcon(R.drawable.search_ic_filter_on);

                    FragmentTransaction ft = getSupportFragmentManager()
                            .beginTransaction();
                      FilterFragment filterFragment = new FilterFragment();
                    ft.replace(R.id.act_search_fl_container, filterFragment, RECENT_SEARCH_FRAGMENT);
                    ft.commit();

                }else{
                    item.setChecked(false);
                    item.setIcon(R.drawable.search_ic_filter_off);

                    FragmentTransaction ft = getSupportFragmentManager()
                            .beginTransaction();
                    RecentSearchFragment recentSearchFragment = new RecentSearchFragment();
                    ft.replace(R.id.act_search_fl_container, recentSearchFragment, RECENT_SEARCH_FRAGMENT);
                    ft.commit();
                }
                return true;
            }
         }
        return super.onOptionsItemSelected(item);
    }

    MenuItem filterMenuItem;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        filterMenuItem = menu.findItem(R.id.search_menu_filter);
        filterMenuItem.setVisible(true);
//        서치뷰 넣을지 생각좀 해보라
        return true;
    }




}
