package com.weddingsingers.wsapp.function.mypage.singervideomgm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SingerVideoMgm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class SingerVideoMgmAdapter extends RecyclerView.Adapter<SingerVideoMgmViewholder> {

    ArrayList<SingerVideoMgm> items;

    public SingerVideoMgmAdapter(List<SingerVideoMgm> items) {
        this.items = new ArrayList<>(items);
    }

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
    public void onBindViewHolder(final SingerVideoMgmViewholder holder, int position) {
        holder.setSingerVideoMgm(items.get(position));
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(items.get(position).isSelected());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //numbers.get(holder.getAdapterPosition()).setSelected(isChecked);
                items.get(holder.getAdapterPosition()).setSelected(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
