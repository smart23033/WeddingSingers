package com.weddingsingers.wsapp.function.video.singerreview;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Review;
import com.weddingsingers.wsapp.data.VideoList;
import com.weddingsingers.wsapp.main.home.VideoListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerReviewFragment extends Fragment {

    @BindView(R.id.view_profile_btn_reserve)
    Button reserveBtn;

    @BindView(R.id.singer_review_rv_list)
    RecyclerView recyclerView;

    SingerReviewAdapter mAdapter;

    public SingerReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_singer_review, container, false);

        ButterKnife.bind(this, view);
        reserveBtn.setVisibility(View.GONE);

        mAdapter = new SingerReviewAdapter();
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        initData();

        return view;
    }

    private void initData() {
        for(int i = 0; i < 10; i++){
            Review review = new Review();
            //videoList.setThumbnail(ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher));
            review.setUserName("사용자 " + i);
            review.setContent("이분 채소 아" + i + "유");
//            review.setDate("2016. 8. 24");
            mAdapter.add(review);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                getActivity().finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
