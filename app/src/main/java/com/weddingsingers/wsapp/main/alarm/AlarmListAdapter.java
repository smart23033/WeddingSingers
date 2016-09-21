package com.weddingsingers.wsapp.main.alarm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Alarm;
import com.weddingsingers.wsapp.data.viewholder.AlarmListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-21.
 */
public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListViewHolder> implements
    AlarmListViewHolder.OnItemClickListener{

    List<Alarm> items = new ArrayList<>();


    public void add(Alarm alarm) {
        items.add(alarm);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public AlarmListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_alarm, parent,false);
        AlarmListViewHolder holder = new AlarmListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AlarmListViewHolder holder, int position) {
        holder.setAlarm(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnAdapterItemClickListener{
        public void onAdapterItemClick(View view, Alarm alarm, int position);
    }

    OnAdapterItemClickListener listener;
    public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(View view, Alarm alarm, int position) {
        if(listener != null){
            listener.onAdapterItemClick(view,alarm,position);
        }
    }
}
