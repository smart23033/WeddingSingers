package com.weddingsingers.wsapp.main.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.VideoList;
import com.weddingsingers.wsapp.data.viewholder.VideoListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SJSJ on 2016-07-28.
 */
public class VideoListAdapter extends RecyclerView.Adapter<VideoListViewHolder>
        implements VideoListViewHolder.OnVideoListItemClickListener{


    private static final String DEVELOPER_KEY = "AIzaSyBE7cGgtf2Dts693vD_JKXM0c_WDVtwg1M";

    List<VideoList> items = new ArrayList<>();

    public void add(VideoList videoList) {
        items.add(videoList);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public VideoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_video_list, parent,false);
        VideoListViewHolder holder = new VideoListViewHolder(view);
        holder.setOnVideoListItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(final VideoListViewHolder holder, int position) {
        holder.setVideoList(items.get(position));

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
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public interface OnAdapterItemClickListener{
        public void onAdapterItemClick(View view, VideoList videoList, int position);
    }

    OnAdapterItemClickListener listener;
    public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onVideoListItemClick(View view, VideoList videoList, int position) {
        if(listener != null){
            listener.onAdapterItemClick(view, videoList, position);
        }
    }
}
