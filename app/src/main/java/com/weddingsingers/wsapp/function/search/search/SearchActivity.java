package com.weddingsingers.wsapp.function.search.search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Search;
import com.weddingsingers.wsapp.data.SearchResult;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.SearchRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    final static String FRAG_RECENT_SEARCH = "RecentSearchFragment";
    final static String FRAG_FILTER = "FilterFragment";
    final static String FRAG_SEACH_RESULT = "SearchResultFragment";
    final static String FRAG_EMPTY_RESULT = "EmptyResultFragment";
    final static int INITIAL_VALUE = 0;

    final static String KEY_SEARCH = "Search";

    @BindView(R.id.search_et_keyword)
    EditText keywordInput;

    @BindView(R.id.search_tv_keyword)
    TextView keywordView;

    @BindView(R.id.search_toolbar)
    Toolbar toolbar;

    Search search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        search = new Search();

        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        RecentSearchFragment recentSearchFragment = RecentSearchFragment.newInstance(search);
        ft.add(R.id.act_search_fl_container, recentSearchFragment, FRAG_RECENT_SEARCH);
        ft.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
            case R.id.main_menu_search: {

                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.act_search_fl_container);

                if (currentFragment.getTag() == FRAG_FILTER || currentFragment.getTag() == FRAG_RECENT_SEARCH) {
//               searchResultFragment가 아닌 프레그먼트(filterFrag랑 RecentSearchFrag)에서 search버튼 누를 때
                    keywordView.setText(keywordInput.getText().toString());
                    keywordView.setVisibility(View.VISIBLE);

                    search.setKeyword(keywordInput.getText().toString());

                    keywordInput.setText("");
                    keywordInput.setVisibility(View.GONE);

                    if (currentFragment.getTag() == FRAG_FILTER) {
                        search.setLocation(((FilterFragment) currentFragment).search.getLocation());
                        search.setComposition(((FilterFragment) currentFragment).search.getComposition());
                        search.setPrice(((FilterFragment) currentFragment).search.getPrice());
                        search.setStartDate(((FilterFragment) currentFragment).search.getStartDate());
                        search.setEndDate(((FilterFragment) currentFragment).search.getEndDate());
                        search.setTheme(((FilterFragment) currentFragment).search.getTheme());

                    } else if (currentFragment.getTag() == FRAG_RECENT_SEARCH) {
                        search.setLocation(INITIAL_VALUE);
                        search.setComposition(INITIAL_VALUE);
                        search.setPrice(INITIAL_VALUE);
//                        search.setStartDate(((FilterFragment) currentFragment).search.getStartDate());
//                        search.setEndDate(((FilterFragment) currentFragment).search.getEndDate());
                        search.setTheme(INITIAL_VALUE);

                    }

                    Log.i("SearchActivity", "keyword : " + search.getKeyword());
                    Log.i("SearchActivity", "location : " + search.getLocation());
                    Log.i("SearchActivity", "price : " + search.getPrice());
                    Log.i("SearchActivity", "composition : " + search.getComposition());
                    Log.i("SearchActivity", "theme : " + search.getTheme());

                    SearchRequest request = new SearchRequest(this, search);
                    NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<SearchResult>>>() {
                        @Override
                        public void onSuccess(NetworkRequest<NetworkResult<List<SearchResult>>> request, NetworkResult<List<SearchResult>> result) {
                            Toast.makeText(SearchActivity.this, "Search Success!", Toast.LENGTH_SHORT).show();

                            if (result.getResult().size() != 0) {
                                FragmentTransaction ft = getSupportFragmentManager()
                                        .beginTransaction();
                                SearchResultFragment searchResultFragment = SearchResultFragment.newInstance(search);
                                ft.replace(R.id.act_search_fl_container, searchResultFragment, FRAG_SEACH_RESULT);
                                ft.commit();
                            } else {
                                Log.i("SearchResultFragment", "result.getResult() 없다.");
                                FragmentTransaction ft = getSupportFragmentManager()
                                        .beginTransaction();
                                EmptyResultFragment emptyResultFragment = new EmptyResultFragment();
                                ft.replace(R.id.act_search_fl_container, emptyResultFragment,FRAG_EMPTY_RESULT);
                                ft.commit();
                            }
                        }

                        @Override
                        public void onFail(NetworkRequest<NetworkResult<List<SearchResult>>> request, int errorCode, String errorMessage, Throwable e) {
                            Toast.makeText(SearchActivity.this, "Search fail : " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });


                } else {
//               searchResultFragment에서 search버튼 누를 때

                    keywordView.setText("");
                    keywordView.setVisibility(View.GONE);
                    keywordInput.setVisibility(View.VISIBLE);

                    search = new Search();

                    FragmentTransaction ft = getSupportFragmentManager()
                            .beginTransaction();
                    RecentSearchFragment recentSearchFragment = RecentSearchFragment.newInstance(search);
                    ft.replace(R.id.act_search_fl_container, recentSearchFragment, FRAG_RECENT_SEARCH);
                    ft.commit();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
