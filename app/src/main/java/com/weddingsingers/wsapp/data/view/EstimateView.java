package com.weddingsingers.wsapp.data.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class EstimateView extends FrameLayout {
    public EstimateView(Context context) {
        this(context, null);
    }

    public EstimateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @BindView(R.id.view_estimate_riv_profile)
    RoundedImageView userImageView = new RoundedImageView(getContext());

    @BindView(R.id.view_estimate_tv_user_name)
    TextView userNameView;

    @BindView(R.id.view_estimate_tv_detail_date)
    TextView dateView;

    @BindView(R.id.view_estimate_tv_detail_location)
    TextView locationView;

    @BindView(R.id.view_estimate_tv_detail_songs)
    TextView songsView;

    @BindView(R.id.view_estimate_tv_standard)
    TextView standardView;

    @BindView(R.id.view_estimate_tv_special)
    TextView specialView;

    private void init(){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.view_estimate,this);

        ButterKnife.bind(this);

        userImageView.mutateBackground(true);
        userImageView.setOval(true);
        userImageView.setBackgroundColor(Color.LTGRAY);
    }

    Estimate estimate;

    public void setEstimate(Estimate estimate){
        this.estimate = estimate;

//         고객이면 싱어의 이름과 이미지를 set, 싱어면 고객의 이름과 이미지를 set해줘야 한다.

        dateView.setText(estimate.getDate());
        locationView.setText(estimate.getLocation());
        songsView.setText(estimate.getSongs());

//        스탠다드인지 스페셜인지 둘중에 하나
        standardView.setText(estimate.getStandard());
        specialView.setText(estimate.getSpecial());
    }
}