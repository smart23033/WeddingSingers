package com.weddingsingers.wsapp.function.mypage.singervideomgm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

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

    List<VideoList> items = new ArrayList<>();
    SparseBooleanArray itemSelected = new SparseBooleanArray();

    public void add(VideoList videoList) {
        items.add(videoList);
        notifyDataSetChanged();
    }

    public void clear(){
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
    public void onBindViewHolder(final SingerVideoMgmViewholder holder, int position) {
        holder.setSingerVideoMgm(items.get(position));
        holder.setChecked(itemSelected.get(position));
//        holder.checkBox.setOnCheckedChangeListener(null);
//        holder.checkBox.setChecked(items.get(position).isSelected());
//        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

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

        boolean checked = itemSelected.get(position);
        setItemChecked(position, !checked);

        if (listener != null) {
            listener.onAdapterItemClick(view, videoList, position);
        }
    }

    public void setItemChecked(int position, boolean isChecked) {

        boolean checked = itemSelected.get(position);
        if (checked != isChecked) {
            itemSelected.put(position, isChecked);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}