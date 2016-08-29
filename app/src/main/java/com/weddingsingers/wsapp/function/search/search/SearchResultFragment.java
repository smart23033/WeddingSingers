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
import com.weddingsingers.wsapp.data.SearchResult;
import com.weddingsingers.wsapp.function.video.video.VideoActivity;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment {

    private static final int RC_VIDEO = 0;

    public SearchResultFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.search_result_rv_list)
    RecyclerView recyclerView;

    @BindView(R.id.search_result_rg_radio)
    RadioGroup radioGroup;

    @BindView(R.id.search_result_rb_popular)
    RadioButton popularBtn;

    SearchResultAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new SearchResultAdapter();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        ButterKnife.bind(this,view);

        recyclerView.setAdapter(mAdapter);

        popularBtn.setChecked(true);

        mAdapter.setOnAdapterItemClickListener(new SearchResultAdapter.OnAdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, SearchResult searchResult, int position) {
                Toast.makeText(getContext(),"SearchResult : " + searchResult.getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), VideoActivity.class);
                intent.putExtra(VideoActivity.EXTRA_SEARCH_RESULT,searchResult);

                startActivity(intent);
            }
        });

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager);

        initData();

        return view;

    }

    private void initData(){
        for(int i=0;i<20;i++){
            SearchResult searchResult = new SearchResult();
            searchResult.setTitle("title " + i);
            searchResult.setSingerName("singerName "  + i);
            searchResult.setHit(i);
            searchResult.setFavorite(i);
            searchResult.setDate("2016. 4. 24");
            mAdapter.add(searchResult);
        }
    }



}
