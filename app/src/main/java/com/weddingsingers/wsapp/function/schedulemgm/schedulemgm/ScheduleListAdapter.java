package com.weddingsingers.wsapp.function.schedulemgm.schedulemgm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.viewholder.ScheduleListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-31.
 */

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListViewHolder> implements
        ScheduleListViewHolder.OnCancelBtnClickListener,
        ScheduleListViewHolder.OnChatBtnClickListener {

    List<Estimate> items = new ArrayList<>();

    public void add(Estimate e){
        items.add(e);
        notifyDataSetChanged();
    }

    public void add(int estimateId){
        for(Estimate e : items){
            if(e.getId() == estimateId) {
                items.add(e);
                notifyDataSetChanged();
            }
        }
    }
    public void remove(int estimateId){
        for(Estimate e : items){
            if(e.getId() == estimateId) {
                items.remove(e);
                notifyDataSetChanged();
            }
        }
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public ScheduleListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_schedule,parent,false);
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
        public void onAdapterChatBtnClick(View view, Estimate profile, int position);
    }


    OnAdapterChatBtnClickListener chatBtnListener;
    public void setOnAdapterChatBtnClickListener(OnAdapterChatBtnClickListener chatBtnListener){
        this.chatBtnListener = chatBtnListener;
    }


    public interface OnAdapterCancelBtnClickListener{
        public void onAdapterCancelBtnClick(View view, Estimate estimate, int position);
    }

    OnAdapterCancelBtnClickListener cancelBtnListener;
    public void setOnAdapterCancelBtnClickListener(OnAdapterCancelBtnClickListener cancelBtnListener){
        this.cancelBtnListener = cancelBtnListener;
    }

    @Override
    public void onCancelBtnClick(View view, Estimate estimate, int position) {
        if(cancelBtnListener != null){
            cancelBtnListener.onAdapterCancelBtnClick(view,estimate,position);
        }
    }

    @Override
    public void onChatBtnClick(View view, Estimate estimate, int position) {
        if(chatBtnListener != null){
            chatBtnListener.onAdapterChatBtnClick(view,estimate,position);
        }
    }

}
