package com.weddingsingers.wsapp.data.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SJSJ on 2016-08-28.
 */

//Compound Widget
public class ProfileView extends FrameLayout {
    private int singerId;
    private String singerName;
    private String singerImage;
    private String comment;
    private int standard;
    private int special;

    public ProfileView(Context context) {
        this(context,null);
    }

    public ProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @BindView(R.id.view_profile_riv_profile)
    RoundedImageView imageView = new RoundedImageView(getContext());

    @BindView(R.id.view_profile_tv_singer_name)
    TextView singerNameView;

    @BindView(R.id.view_profile_tv_comment)
    TextView commentView;

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.view_profile, this);

        ButterKnife.bind(this);

        imageView.mutateBackground(true);
        imageView.setOval(true);
        imageView.setBackgroundColor(Color.LTGRAY);
    }

    //추가 요망
    public void setSingerId(int singerId) {
        this.singerId = singerId;

    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
        singerNameView.setText(singerName);
    }

    //추가 요망
    public void setSingerImage(String singerImage) {
        this.singerImage = singerImage;
        Glide.with(getContext())
                .load(singerImage)
                .centerCrop()
                .crossFade()
                .error(ContextCompat.getDrawable(getContext(), R.drawable.view_profile_ic_person))
                .into(imageView);
    }

    public void setComment(String comment) {
        this.comment = comment;
        commentView.setText(comment);
    }


//    videoFragment를 위한 별도 setter
    public void setStandard(int standard) {
        this.standard = standard;

    }

    //    videoFragment를 위한 별도 setter
    public void setSpecial(int special) {
        this.special = special;
    }
}
