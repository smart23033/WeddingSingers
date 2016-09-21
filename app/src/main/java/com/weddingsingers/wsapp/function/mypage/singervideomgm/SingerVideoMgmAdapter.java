package com.weddingsingers.wsapp.function.mypage.singervideomgm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SingerVideoMgm;
import com.weddingsingers.wsapp.data.VideoList;
import com.weddingsingers.wsapp.data.viewholder.SingerListViewHolder;
import com.weddingsingers.wsapp.data.viewholder.SingerVideoMgmViewholder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class SingerVideoMgmAdapter extends RecyclerView.Adapter<SingerVideoMgmViewholder>
        implements SingerVideoMgmViewholder.OnVideoItemClickListener {

    private static final String DEVELOPER_KEY = "AIzaSyBE7cGgtf2Dts693vD_JKXM0c_WDVtwg1M";

    List<VideoList> items = new ArrayList<>();
    SparseBooleanArray itemSelected = new SparseBooleanArray();

    public void add(VideoList videoList) {
        items.add(videoList);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public SingerVideoMgmViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_singer_video_mgm, parent, false);
        SingerVideoMgmViewholder holder = new SingerVideoMgmViewholder(view);
        holder.setOnVideoItemClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(final SingerVideoMgmViewholder holder, final int position) {
        holder.setSingerVideoMgm(items.get(position));

        final YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

            }
        };

        holder.thumbnailImageView.initialize(DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(holder.getUrl());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

        holder.checkBox.setChecked(items.get(position).isSelected());
        holder.checkBox.setTag(items.get(position));
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox cb = (CheckBox) view;
                VideoList videoList = (VideoList) cb.getTag();
                videoList.setSelected(cb.isChecked());
                items.get(position).setSelected(cb.isChecked());

//                Toast.makeText(view.getContext(), "Clicked on Checkbox: " + videoList.getVideoId() + " is " + cb.isChecked(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public interface OnAdapterItemClickLIstener {
        public void onAdapterItemClick(View view, VideoList videoList, int position);
    }

    OnAdapterItemClickLIstener listener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickLIstener listener) {
        this.listener = listener;
    }

    @Override
    public void onVideoItemClick(View view, VideoList videoList, int position) {

        if (listener != null) {
            listener.onAdapterItemClick(view, videoList, position);
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

}