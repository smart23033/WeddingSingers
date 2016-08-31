package com.weddingsingers.wsapp.function.schedulemgm.schedulemgm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.ScheduleList;
import com.weddingsingers.wsapp.data.viewholder.ScheduleListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-31.
 */

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListViewHolder> implements
        ScheduleListViewHolder.OnCancelBtnClickListener,
        ScheduleListViewHolder.OnChatBtnClickListener
{
    List<ScheduleList> items = new ArrayList<>();

    public void add(ScheduleList s){
        items.add(s);
        notifyDataSetChanged();
    }

    @Override
    public ScheduleListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_calendar,parent,false);
        ScheduleListViewHolder holder = new ScheduleListViewHolder(view);
        holder.setOnCancelBtnClickListener(this);
        holder.setOnChatBtnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ScheduleListViewHolder holder, int position) {
        holder.setScheduleList(items.get(position));
    }

    @Override
    public int getItemCount() {
        return  items.size();
    }

    public interface OnAdapterChatBtnClickListener {
        public void onAdapterChatBtnClick(View view, ScheduleList scheduleList, int position);
    }


    OnAdapterChatBtnClickListener chatBtnListener;
    public void setOnAdapterChatBtnClickListener(OnAdapterChatBtnClickListener chatBtnListener){
        this.chatBtnListener = chatBtnListener;
    }


    public interface OnAdapterCancelBtnClickListener{
        public void onAdapterCancelBtnClick(View view, ScheduleList singerList, int position);
    }

    OnAdapterCancelBtnClickListener cancelBtnListener;
    public void setOnAdapterCancelBtnClickListener(OnAdapterCancelBtnClickListener cancelBtnListener){
        this.cancelBtnListener = cancelBtnListener;
    }

    @Override
    public void onCancelBtnClick(View view, ScheduleList scheduleList, int position) {
        if(cancelBtnListener != null){
            cancelBtnListener.onAdapterCancelBtnClick(view,scheduleList,position);
        }
    }

    @Override
    public void onChatBtnClick(View view, ScheduleList scheduleList, int position) {
        if(chatBtnListener != null){
            chatBtnListener.onAdapterChatBtnClick(view,scheduleList,position);
        }
    }

}
