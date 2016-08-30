package com.weddingsingers.wsapp.main.reservationmgm;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SingerList;
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

    List<SingerList> items = new ArrayList<>();

    public void add(SingerList singerList) {
        items.add(singerList);
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
        public void onAdapterPayBtnClick(View view, SingerList singerList, int position);
    }

    OnAdapterPayBtnClickListener payBtnListener;
    public void setOnAdapterPayBtnClickListener(OnAdapterPayBtnClickListener payBtnListener){
        this.payBtnListener = payBtnListener;
    }


    public interface OnAdapterChatBtnClickListener{
        public void onAdapterChatBtnClick(View view, SingerList singerList, int position);
    }

    OnAdapterChatBtnClickListener chatBtnListener;
    public void setOnAdapterChatBtnClickListener(OnAdapterChatBtnClickListener chatBtnListener){
        this.chatBtnListener = chatBtnListener;
    }


    public interface OnAdapterCancelBtnClickListener{
        public void onAdapterCancelBtnClick(View view, SingerList singerList, int position);
    }

    OnAdapterCancelBtnClickListener cancelBtnListener;
    public void setOnAdapterCancelBtnClickListener(OnAdapterCancelBtnClickListener cancelBtnListener){
        this.cancelBtnListener = cancelBtnListener;
    }

    @Override
    public void onPayBtnClick(View view, SingerList singerList, int position) {
        if(payBtnListener != null){
            payBtnListener.onAdapterPayBtnClick(view,singerList,position);
        }
    }

    @Override
    public void onCancelBtnClick(View view, SingerList singerList, int position) {
        if(cancelBtnListener != null){
            cancelBtnListener.onAdapterCancelBtnClick(view,singerList,position);
        }
    }

    @Override
    public void onChatBtnClick(View view, SingerList singerList, int position) {
        if(chatBtnListener != null){
            chatBtnListener.onAdapterChatBtnClick(view,singerList,position);
        }
    }
}