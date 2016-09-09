package com.weddingsingers.wsapp.function.review.writereview;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Review;
import com.weddingsingers.wsapp.data.Singer;
import com.weddingsingers.wsapp.function.search.search.FilterSpinnerAdapter;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.SingerMyProfileRequest;
import com.weddingsingers.wsapp.request.WriteReviewRequest;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class WriteReviewFragment extends Fragment {

    @BindView(R.id.write_review_sp_detail_bill_num)
    Spinner billSpinner;

    @BindView(R.id.singer_profile_modify_et_songs)
    EditText songsInput;

    final static int FRAG_MY_PAGE = 200;

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

        initData();

        return view;
    }

    Review review;

    public void initData() {
        billAdapter.clear();

        SingerMyProfileRequest request = new SingerMyProfileRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Singer>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Singer>> request, NetworkResult<Singer> result) {

//                singerGet.setSingerName(result.getResult().getSingerName());
//                singerGet.setComment(result.getResult().getComment());
//                singerGet.setLocation(result.getResult().getLocation());
//                singerGet.setComposition(result.getResult().getComposition());
//                singerGet.setTheme(result.getResult().getTheme());
//                singerGet.setDescription(result.getResult().getDescription());
//                singerGet.setSpecial(result.getResult().getSpecial());
//                singerGet.setStandard(result.getResult().getStandard());
//                singerGet.setSongs(result.getResult().getSongs());

                String[] items = getResources().getStringArray(R.array.location);
                billAdapter.addAll(items);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Singer>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getActivity(), "SingerProfileFragment fail - " + errorMessage, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @OnClick(R.id.write_review_btn_write)
    void onWriteBtnClick() {

        String dTime = "";
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT+9"));
        dTime = dateFormatGmt.format(new Date()).toString();

        review = new Review();
        review.setReservationId(16);
        review.setSingerId(1);
        review.setPoint("" + reviewRatingBar.getRating());
        review.setContent(contentInput.getText().toString());
        review.setWrtieDTime(dTime);

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
