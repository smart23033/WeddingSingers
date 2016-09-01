package com.weddingsingers.wsapp.function.search.search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    final static String FRAG_RECENT_SEARCH = "RecentSearchFragment";
    final static String FRAG_FILTER = "FilterFragment";
    final static String FRAG_SEACH_RESULT = "SearchResultFragment";


    @BindView(R.id.search_et_keyword)
    EditText keywordInput;

    @BindView(R.id.search_tv_keyword)
    TextView keywordView;

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
        ft.add(R.id.act_search_fl_container,recentSearchFragment, FRAG_RECENT_SEARCH);
        ft.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Toast.makeText(SearchActivity.this,"SearchActivity's HomeAsUp Clicked", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            }
            case R.id.main_menu_search: {
                Toast.makeText(SearchActivity.this, "SearchActivity's Search Button Clicked", Toast.LENGTH_SHORT).show();

                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.act_search_fl_container);

                if (currentFragment.getTag() != FRAG_SEACH_RESULT) {
//               searchResultFragment가 아닌 프레그먼트(filterFrag랑 RecentSearchFrag)에서 search버튼 누를 때
                    keywordView.setText(keywordInput.getText());
                    keywordView.setVisibility(View.VISIBLE);
                    keywordInput.setText("");
                    keywordInput.setVisibility(View.GONE);

                    FragmentTransaction ft = getSupportFragmentManager()
                            .beginTransaction();
                    SearchResultFragment searchResultFragment = new SearchResultFragment();
                    ft.replace(R.id.act_search_fl_container, searchResultFragment, FRAG_SEACH_RESULT);
                    ft.commit();
                } else {
//               searchResultFragment에서 search버튼 누를 때
                    keywordView.setText("");
                    keywordView.setVisibility(View.GONE);
                    keywordInput.setVisibility(View.VISIBLE);

                    FragmentTransaction ft = getSupportFragmentManager()
                            .beginTransaction();
                    RecentSearchFragment recentSearchFragment = new RecentSearchFragment();
                    ft.replace(R.id.act_search_fl_container, recentSearchFragment, FRAG_RECENT_SEARCH);
                    ft.commit();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
