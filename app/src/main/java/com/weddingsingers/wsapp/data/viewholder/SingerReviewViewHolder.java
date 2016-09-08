package com.weddingsingers.wsapp.data.viewholder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Review;
import com.weddingsingers.wsapp.data.VideoList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class SingerReviewViewHolder extends RecyclerView.ViewHolder {

    Context context = MyApplication.getContext();

    @BindView(R.id.view_singer_review_riv_picture)
    RoundedImageView thumbnailImageView;

    @BindView(R.id.view_singer_review_tv_name)
    TextView nameView;

    @BindView(R.id.view_singer_review_tv_review)
    TextView contentView;

    public SingerReviewViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        thumbnailImageView.mutateBackground(true);
        thumbnailImageView.setOval(true);

    }

    Review review;

    public void setReview(Review review) {
        this.review = review;

        Glide.with(context)
                .load(review.getThumbnail())
                //.load("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQg7S3iXqkm3eh5jVd6Or7tlCF7iAv3XeDwWwxqSsVyCBPng0inR9Pn9H0")
                .centerCrop()
                .crossFade()
                .error(ContextCompat.getDrawable(context, R.drawable.ic_nav_logout))
                .into(thumbnailImageView);
        nameView.setText(review.getCustomerName());
        contentView.setText(review.getContent());
//        dateView.setText(review.getDate());
    }

}
