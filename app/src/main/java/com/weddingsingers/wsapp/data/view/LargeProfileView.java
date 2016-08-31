package com.weddingsingers.wsapp.data.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.LargeProfile;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class LargeProfileView extends FrameLayout {
    public LargeProfileView(Context context) {
        this(context, null);
    }

    public LargeProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @BindView(R.id.view_large_profile_riv_profile)
    RoundedImageView userImageView = new RoundedImageView(getContext());

    @BindView(R.id.view_large_profile_tv_user_name)
    TextView userNameView;

    @BindView(R.id.view_large_profile_tv_detail_date)
    TextView dateView;

    @BindView(R.id.view_large_profile_tv_detail_location)
    TextView locationView;

    @BindView(R.id.view_large_profile_tv_detail_songs)
    TextView songsView;

    @BindView(R.id.view_large_profile_tv_standard)
    TextView standardView;

    @BindView(R.id.view_large_profile_tv_special)
    TextView specialView;

    private void init(){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.view_large_profile,this);

        ButterKnife.bind(this);

        userImageView.mutateBackground(true);
        userImageView.setOval(true);
        userImageView.setBackgroundColor(Color.LTGRAY);
    }

    LargeProfile profile;

    public void setLargeProfile(LargeProfile profile){
        this.profile = profile;

//        userImageView
        userNameView.setText(profile.getUserName());
        dateView.setText(profile.getDate());
        locationView.setText(profile.getLocation());
        standardView.setText(profile.getStandard());
        specialView.setText(profile.getSpecial());
    }
}