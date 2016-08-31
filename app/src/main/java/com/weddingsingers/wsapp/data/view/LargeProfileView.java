package com.weddingsingers.wsapp.data.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;

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
    RoundedImageView imageView = new RoundedImageView(getContext());

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

        imageView.mutateBackground(true);
        imageView.setOval(true);
        imageView.setBackgroundColor(Color.LTGRAY);
    }



}