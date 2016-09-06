package com.weddingsingers.wsapp.function.search.search;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Search;
import com.weddingsingers.wsapp.data.SearchResult;
import com.weddingsingers.wsapp.function.video.video.VideoActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.SearchRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment {

    @BindView(R.id.search_result_rv_list)
    RecyclerView recyclerView;

    @BindView(R.id.search_result_rg_radio)
    RadioGroup radioGroup;

    @BindView(R.id.search_result_rb_popular)
    RadioButton popularBtn;

    SearchResultAdapter mAdapter;

    public SearchResultFragment() {
        // Required empty public constructor
    }


    public static SearchResultFragment newInstance(Search search) {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle b = new Bundle();
        b.putSerializable(SearchActivity.KEY_SEARCH, search);
        fragment.setArguments(b);
        return fragment;
    }

    Search search;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new SearchResultAdapter();
        if (getArguments() != null) {
            search = (Search) getArguments().getSerializable(SearchActivity.KEY_SEARCH);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setAdapter(mAdapter);

        popularBtn.setChecked(true);

        mAdapter.setOnAdapterItemClickListener(new SearchResultAdapter.OnAdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, SearchResult searchResult, int position) {
                Toast.makeText(getContext(), "SearchResult : " + searchResult.getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), VideoActivity.class);
                intent.putExtra(VideoActivity.EXTRA_SEARCH_RESULT, searchResult.getId());
                startActivity(intent);
            }
        });

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(manager);

        initData();

        return view;

    }

    private void initData() {

        SearchRequest request = new SearchRequest(getContext(), search);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<SearchResult>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<SearchResult>>> request, NetworkResult<List<SearchResult>> result) {
                Toast.makeText(getContext(), "Search Success!", Toast.LENGTH_SHORT).show();

                for (SearchResult sr : result.getResult()) {
                    SearchResult searchResult = new SearchResult();
                    searchResult.setDate(sr.getDate());
                    searchResult.setTitle(sr.getTitle());
                    searchResult.setFavorite(sr.getFavorite());
                    searchResult.setHit(sr.getHit());
                    searchResult.setId(sr.getId());
                    searchResult.setSingerName(sr.getSingerName());
                    searchResult.setThumbnail(sr.getThumbnail());
                    searchResult.setId(sr.getId());
                    mAdapter.add(searchResult);
                }

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<SearchResult>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "Search fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
