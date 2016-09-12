package com.weddingsingers.wsapp.function.review.writereview;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Review;
import com.weddingsingers.wsapp.function.search.search.FilterSpinnerAdapter;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.EstimateListRequest;
import com.weddingsingers.wsapp.request.WriteReviewRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * A simple {@link Fragment} subclass.
 */
public class WriteReviewFragment extends Fragment {

    final static int FRAG_MY_PAGE = 200;
    private static final int TAB_RESERVED_ONE = 2;

    @BindView(R.id.write_review_sp_detail_bill_num)
    Spinner billSpinner;

    @BindView(R.id.write_review_et_content)
    EditText contentInput;

    @BindView(R.id.write_review_rb_rating)
    RatingBar reviewRatingBar;

    FilterSpinnerAdapter billAdapter;

    public WriteReviewFragment() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_write_review, container, false);

        ButterKnife.bind(this, view);

        billAdapter = new FilterSpinnerAdapter();
        billSpinner.setAdapter(billAdapter);

        reviewRatingBar.setStepSize(1);
        reviewRatingBar.setRating(5);
        reviewRatingBar.setIsIndicator(false);
        /*reviewRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });*/
        initData();

        return view;
    }

    Review review;
    int[] reservationIds;
    int reservationId;

    @OnItemSelected(R.id.write_review_sp_detail_bill_num)
    void onBillSelected(int position) {
        reservationId = reservationIds[billSpinner.getSelectedItemPosition()];
    }

    public void initData() {

        String dTime = "";
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT+9"));
        dTime = dateFormatGmt.format(new Date()).toString();

        billAdapter.clear();

        EstimateListRequest estimateListRequest = new EstimateListRequest(getContext(), TAB_RESERVED_ONE);
        NetworkManager.getInstance().getNetworkData(estimateListRequest, new NetworkManager.OnResultListener<NetworkResult<List<Estimate>>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<List<Estimate>>> request, NetworkResult<List<Estimate>> result) {

                        String[] items = new String[result.getResult().size()];
                        reservationIds = new int[result.getResult().size()];

                        int i = 0;
                        for (Estimate e : result.getResult()) {
                            Estimate estimate = new Estimate();
                            estimate.setId(e.getId());
                            estimate.setSingerName(e.getSingerName());
                            estimate.setDate(e.getDate());

                            items[i] = estimate.getDate() + " - " + estimate.getSingerName();
                            reservationIds[i] = estimate.getId();

                            i++;
                        }

                        billAdapter.addAll(items);
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<List<Estimate>>> request, int errorCode, String errorMessage, Throwable e) {

                    }
                }
        );

    }

    @OnClick(R.id.write_review_btn_write)
    void onWriteBtnClick() {

        String dTime = "";
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT+9"));
        dTime = dateFormatGmt.format(new Date()).toString();

        review = new Review();
        review.setReservationId(reservationId);
        review.setIntPoint((int) reviewRatingBar.getRating());
        review.setContent(contentInput.getText().toString());
        review.setWriteDTime(dTime);

        Log.i("write review ----", reservationId + "-"+ reviewRatingBar.getRating() + "-"+ contentInput.getText().toString() + "-"+ dTime);

        WriteReviewRequest request = new WriteReviewRequest(getContext(), review);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<String>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {

                Toast.makeText(getActivity(), "success code : " + result.getCode(), Toast.LENGTH_SHORT).show();

                getActivity().setResult(Activity.RESULT_OK, new Intent());
                getActivity().finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getActivity(), "SingerMyPageFragment fail - " + errorMessage, Toast.LENGTH_SHORT).show();

            }
        });

        getActivity().setResult(Activity.RESULT_OK, new Intent());
        getActivity().finish();

        /*AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Write Success")
                .setMessage("Write Success,\nyou can check your benefit point in my page")
                .setPositiveButton("GO MY PAGE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        getActivity().finish();
                    }
                });

        builder.setNegativeButton("GO MAIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().finish();
            }
        });

        dialog = builder.create();
        dialog.show();*/
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
