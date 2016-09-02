package com.weddingsingers.wsapp.data.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Review;
import com.weddingsingers.wsapp.data.VideoList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class SingerReviewViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.view_singer_review_riv_picture)
    RoundedImageView thumbnailImageView;

    @BindView(R.id.view_singer_review_tv_name)
    TextView nameView;

    @BindView(R.id.view_singer_review_tv_review)
    TextView contentView;

    public SingerReviewViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

    }

    Review review;

    public void setReview(Review review) {
        this.review = review;
        //thumbnailImageView.setImageBitmap(review.getThumbnail());
        nameView.setText(review.getUserName());
        contentView.setText(review.getContent());
//        dateView.setText(review.getDate());
    }

}
