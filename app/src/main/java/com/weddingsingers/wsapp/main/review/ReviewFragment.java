package com.weddingsingers.wsapp.main.review;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Review;
import com.weddingsingers.wsapp.function.review.writereview.WriteReviewActivity;
import com.weddingsingers.wsapp.function.video.singerreview.SingerReviewAdapter;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.MyReviewRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {

    public static final int TYPE_SINGER = 1;
    public static final int TYPE_CUSTOMER = 2;

    @BindView(R.id.review_rv_list)
    RecyclerView recyclerView;

    SingerReviewAdapter mAdapter;

    public ReviewFragment() {
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
        View view = inflater.inflate(R.layout.fragment_review, container, false);

        ButterKnife.bind(this, view);

        mAdapter = new SingerReviewAdapter(TYPE_CUSTOMER);
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        initData();

        return view;
    }

    public void initData() {
        mAdapter.clear();
        MyReviewRequest request = new MyReviewRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<Review>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Review>>> request, NetworkResult<List<Review>> result) {
//
                for (int i = 0; i < result.getResult().size(); i++) {
                    Review review = new Review();
                    review.setThumbnail(result.getResult().get(i).getThumbnail());
                    review.setContent(result.getResult().get(i).getContent());
                    review.setSingerName(result.getResult().get(i).getSingerName());
                    review.setWriteDTime(result.getResult().get(i).getWriteDTime());
                    review.setPoint(result.getResult().get(i).getPoint());
                    mAdapter.add(review);
                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<Review>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Toast.makeText(getActivity(), "ok : " + resultCode, Toast.LENGTH_SHORT).show();
            initData();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.write_review_write: {
                Intent intent = new Intent(getActivity(), WriteReviewActivity.class);
                startActivityForResult(intent, 1);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.write_review_menu, menu);
    }

}
