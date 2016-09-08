package com.weddingsingers.wsapp.data.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class EstimateView extends FrameLayout {

    private String location;
    private String date;
    private String song;
    private String userName;
    private String userImage;
    private String special;
    private int type;

    public EstimateView(Context context) {
        this(context, null);
    }

    public EstimateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @BindView(R.id.view_estimate_riv_profile)
    RoundedImageView userImageView;

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
        inflater.inflate(R.layout.view_estimate_default,this);

        ButterKnife.bind(this);

        userImageView.mutateBackground(true);
        userImageView.setOval(true);
        userImageView.setBackgroundColor(Color.LTGRAY);
    }

    Estimate estimate;

    public void setEstimate(Estimate estimate){
        this.estimate = estimate;

//         고객이면 싱어의 이름과 이미지를 set, 싱어면 고객의 이름과 이미지를 set해줘야 한다.

        Glide.with(getContext())
                .load(estimate.getUserImage())
                .centerCrop()
                .crossFade()
                .error(ContextCompat.getDrawable(getContext(), R.drawable.view_profile_ic_person))
                .into(userImageView);

        userNameView.setText(estimate.getUserName());
        dateView.setText(estimate.getDate());
        locationView.setText(estimate.getLocation());
        songsView.setText(estimate.getSongs());
        specialView.setText(estimate.getSpecial());

    }

    public void setType(int type) {
        this.type = type;
    }

//    추가요망
    public void setUserImage(String userImage) {
        this.userImage = userImage;
        Glide.with(getContext())
                .load(userImage)
                .centerCrop()
                .crossFade()
                .error(ContextCompat.getDrawable(getContext(), R.drawable.view_profile_ic_person))
                .into(userImageView);
    }

    public void setUserName(String userName) {
        this.userName = userName;
        userNameView.setText(userName);
    }

    public void setSong(String song) {
        this.song = song;
        songsView.setText(song);
    }

    public void setDate(String date) {
        this.date = date;
        dateView.setText(date);
    }

    public void setLocation(String location) {
        this.location = location;
        locationView.setText(location);
    }

    public void setSpecial(String special) {
        this.special = special;
        specialView.setText(special);
    }

}