package com.weddingsingers.wsapp.data.viewholder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.VideoList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class SingerVideoMgmViewholder extends RecyclerView.ViewHolder {

    Context context = MyApplication.getContext();

    @BindView(R.id.view_singer_video_mgm_iv_thumbnail)
    ImageView thumbnailImageView;

    @BindView(R.id.view_singer_video_mgm_tv_title)
    TextView titleView;

    @BindView(R.id.view_singer_video_mgm_tv_date)
    TextView dateView;

    @BindView(R.id.view_singer_video_mgm_tv_hit)
    TextView hitView;

    @BindView(R.id.view_singer_video_mgm_tv_favorite)
    TextView favoriteView;

    @BindView(R.id.view_singer_video_mgm_cb)
    public CheckBox checkBox;

    public interface OnVideoItemClickListener {
        public void onVideoItemClick(View view, VideoList videoList, int position);
    }

    OnVideoItemClickListener listener;
    public void setOnVideoItemClickListener(OnVideoItemClickListener listener) {
        this.listener = listener;
    }

    public SingerVideoMgmViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onVideoItemClick(v, videoList, getAdapterPosition());
                }
            }
        });

    }

    VideoList videoList;

    public void setSingerVideoMgm(VideoList videoList) {
        this.videoList = videoList;

        Glide.with(context)
                .load(videoList.getThumbnail())
                .centerCrop()
                .crossFade()
                .error(ContextCompat.getDrawable(context, R.drawable.ic_nav_logout))
                .into(thumbnailImageView);

        titleView.setText(videoList.getTitle());
        dateView.setText(videoList.getDate());
        hitView.setText("" + videoList.getHit());
        favoriteView.setText("" + videoList.getFavorite());
    }

}