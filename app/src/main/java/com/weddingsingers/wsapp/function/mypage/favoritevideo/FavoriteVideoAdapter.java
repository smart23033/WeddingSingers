package com.weddingsingers.wsapp.function.mypage.favoritevideo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.VideoList;
import com.weddingsingers.wsapp.data.viewholder.VideoListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-07.
 */
public class FavoriteVideoAdapter extends RecyclerView.Adapter<VideoListViewHolder>
        implements VideoListViewHolder.OnVideoListItemClickListener{

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
    public void onBindViewHolder(VideoListViewHolder holder, int position) {
        holder.setVideoList(items.get(position));
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
