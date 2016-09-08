package com.weddingsingers.wsapp.function.video.singerreview;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Rating;
import com.weddingsingers.wsapp.data.Review;
import com.weddingsingers.wsapp.data.VideoList;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.FavoriteVideoListRequest;
import com.weddingsingers.wsapp.request.RatingRequest;
import com.weddingsingers.wsapp.request.SingerReviewRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerReviewFragment extends Fragment {

    final static int ARG_RATING = 1;

    @BindView(R.id.singer_review_tv_num_of_person)
    TextView countView;

    @BindView(R.id.singer_review_rb_rating)
    RatingBar ratingBar;

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

        mAdapter = new SingerReviewAdapter();
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        initData();

        return view;
    }

    int singerId = 3;

    private void initData() {

        RatingRequest ratingRequest = new RatingRequest(getContext(),singerId,ARG_RATING);
        NetworkManager.getInstance().getNetworkData(ratingRequest, new NetworkManager.OnResultListener<NetworkResult<Rating>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Rating>> request, NetworkResult<Rating> result) {

                int reviewCnt = result.getResult().getReviewCnt();
                float reviewPoint = result.getResult().getReviewPoint();

                countView.setText("" + reviewCnt);
                ratingBar.setRating(reviewPoint);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Rating>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });

        SingerReviewRequest request = new SingerReviewRequest(getContext(), singerId);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<Review>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Review>>> request, NetworkResult<List<Review>> result) {
//                    여기에 어댑터에 들어갈 놈들이 쌓여야 한다.
                for (int i = 0; i < result.getResult().size(); i++) {
                    Review review = new Review();
                    review.setThumbnail(result.getResult().get(i).getThumbnail());
                    review.setContent(result.getResult().get(i).getContent());
                    review.setCustomerName(result.getResult().get(i).getCustomerName());
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
