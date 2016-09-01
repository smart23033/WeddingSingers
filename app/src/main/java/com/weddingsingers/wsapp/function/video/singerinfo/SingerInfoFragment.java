package com.weddingsingers.wsapp.function.video.singerinfo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.video.othervideo.OtherVideoActivity;
import com.weddingsingers.wsapp.function.video.singerreview.SingerReviewActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerInfoFragment extends Fragment {

    public SingerInfoFragment() {
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

        View view = inflater.inflate(R.layout.fragment_singer_info, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.singer_info_rl_review)
    public void onReviewClick() {
        startActivity(new Intent(getActivity(), SingerReviewActivity.class));
    }

    @OnClick(R.id.singer_info_rl_other)
    public void onOtherVideoClick() {
        startActivity(new Intent(getActivity(), OtherVideoActivity.class));
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
