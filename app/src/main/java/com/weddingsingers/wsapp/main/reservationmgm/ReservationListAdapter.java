package com.weddingsingers.wsapp.main.reservationmgm;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.viewholder.SingerListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class ReservationListAdapter extends RecyclerView.Adapter<SingerListViewHolder> implements
        SingerListViewHolder.OnPayBtnClickListener ,
        SingerListViewHolder.OnCancelBtnClickListener,
        SingerListViewHolder.OnChatBtnClickListener{

    List<Estimate> items = new ArrayList<>();

    public void add(Estimate estimate) {
        items.add(estimate);
        notifyDataSetChanged();
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
    public SingerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_reserved_singer,parent,false);
        SingerListViewHolder holder = new SingerListViewHolder(view);

        holder.setOnPayBtnClickListener(this);
        holder.setOnCancelBtnClickListener(this);
        holder.setOnChatBtnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(SingerListViewHolder holder, int position) {
        holder.setSingerList(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    public interface OnAdapterPayBtnClickListener{
        public void onAdapterPayBtnClick(View view, Estimate estimate, int position);
    }

    OnAdapterPayBtnClickListener payBtnListener;
    public void setOnAdapterPayBtnClickListener(OnAdapterPayBtnClickListener payBtnListener){
        this.payBtnListener = payBtnListener;
    }


    public interface OnAdapterChatBtnClickListener{
        public void onAdapterChatBtnClick(View view, Estimate estimate, int position);
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
    public void onPayBtnClick(View view, Estimate estimate, int position) {
        if(payBtnListener != null){
            payBtnListener.onAdapterPayBtnClick(view,estimate,position);
        }
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