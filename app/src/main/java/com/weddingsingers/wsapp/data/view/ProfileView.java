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


//    여기서부터 다시한다!

    public ProfileView(Context context, int singerId, String singerName, String singerImage,String comment) {
        super(context);
        this.singerId = singerId;
        this.singerName = singerName;
        this.singerImage = singerImage;
        this.comment = comment;
        init();
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

        Log.i("ProfileView", "singerName : " + singerName);

        singerNameView.setText(singerName);
        commentView.setText(comment);


    }

}
