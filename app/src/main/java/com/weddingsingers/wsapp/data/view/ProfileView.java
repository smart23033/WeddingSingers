package com.weddingsingers.wsapp.data.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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

        singerNameView.setText(singerName);
        commentView.setText(comment);

        Log.i("ProfileView","singerId : " + singerId);
        Log.i("ProfileView","singerName : " + singerName);
        Log.i("ProfileView","comment : " + comment);

    }


    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public void setSingerImage(String singerImage) {
        this.singerImage = singerImage;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
