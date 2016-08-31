package com.weddingsingers.wsapp.function.mypage.singervideomgm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SingerVideoMgm;
import com.weddingsingers.wsapp.data.VideoList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class SingerVideoMgmAdapter extends RecyclerView.Adapter<SingerVideoMgmViewholder> {

    List<SingerVideoMgm> items = new ArrayList<>();

    public void add(SingerVideoMgm singerVideoMgm) {
        items.add(singerVideoMgm);
        notifyDataSetChanged();
    }

    @Override
    public SingerVideoMgmViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_singer_video_mgm, parent,false);
        SingerVideoMgmViewholder holder = new SingerVideoMgmViewholder(view);
//        holder.setOnVideoListItemClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(SingerVideoMgmViewholder holder, int position) {
        holder.setSingerVideoMgm(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
