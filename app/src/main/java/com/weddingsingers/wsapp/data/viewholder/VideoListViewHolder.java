package com.weddingsingers.wsapp.data.viewholder;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.VideoList;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SJSJ on 2016-07-28.
 */
public class VideoListViewHolder extends RecyclerView.ViewHolder {

    Context context = MyApplication.getContext();

    @BindView(R.id.view_video_list_iv_thumbnail)
    ImageView thumbnailImageView;

    @BindView(R.id.view_video_list_tv_title)
    TextView titleView;

    @BindView(R.id.view_video_list_tv_date)
    TextView dateView;

    @BindView(R.id.view_video_list_tv_hit)
    TextView hitView;

    @BindView(R.id.view_video_list_tv_favorite)
    TextView favoriteView;

    public interface OnVideoListItemClickListener {
        public void onVideoListItemClick(View view, VideoList videoList, int position);
    }

    OnVideoListItemClickListener listener;
    public void setOnVideoListItemClickListener(OnVideoListItemClickListener listener) {
        this.listener = listener;
    }

    public VideoListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onVideoListItemClick(view, videoList, getAdapterPosition());
                }
            }
        });
    }

    VideoList videoList;

    public void setVideoList(VideoList videoList) {

        this.videoList = videoList;

        Glide.with(context)
                .load(videoList.getThumbnail())
                .centerCrop()
                .crossFade()
                .error(ContextCompat.getDrawable(context, R.drawable.video_img_default_thumbnail))
                .into(thumbnailImageView);

        //thumbnailImageView.setImageBitmap();
        titleView.setText(videoList.getTitle());
        dateView.setText(videoList.getDate());
        hitView.setText("" + videoList.getHit());
        favoriteView.setText("" + videoList.getFavorite());
    }
}