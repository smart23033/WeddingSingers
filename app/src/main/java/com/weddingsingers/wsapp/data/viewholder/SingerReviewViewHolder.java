package com.weddingsingers.wsapp.data.viewholder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Review;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class SingerReviewViewHolder extends RecyclerView.ViewHolder {

    Context context = MyApplication.getContext();
    public static final int TYPE_SINGER = 1;
    public static final int TYPE_CUSTOMER = 2;
    int type = 0;

    @BindView(R.id.view_singer_review_riv_picture)
    RoundedImageView thumbnailImageView;

    @BindView(R.id.view_singer_review_tv_name)
    TextView nameView;

    @BindView(R.id.view_singer_review_tv_data)
    TextView dateView;

    @BindView(R.id.view_singer_review_tv_review)
    TextView contentView;

    public SingerReviewViewHolder(View itemView , int type) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        this.type = type;

        thumbnailImageView.mutateBackground(true);
        thumbnailImageView.setOval(true);

    }

    Review review;

    public void setReview(Review review) {

        String userName = "";
        String thumbnail = "";



        if (type == TYPE_SINGER) {
            userName = review.getCustomerName();
            thumbnail = review.getCustomerThumbnail();
        } else {
            userName = review.getSingerName();
            thumbnail = review.getThumbnail();
        }

        Log.i("setReview - ", type + " - " + userName +" - " + thumbnail);

        this.review = review;

        Glide.with(context)
                .load(thumbnail)
                .centerCrop()
                .crossFade()
                .error(ContextCompat.getDrawable(context, R.drawable.ic_nav_logout))
                .into(thumbnailImageView);
        nameView.setText(userName);
        dateView.setText(review.getWriteDTime());
        contentView.setText(review.getContent());
    }

}
