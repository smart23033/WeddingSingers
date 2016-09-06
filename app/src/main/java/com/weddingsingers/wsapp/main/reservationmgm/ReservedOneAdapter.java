package com.weddingsingers.wsapp.main.reservationmgm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.viewholder.EstimateViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class ReservedOneAdapter extends RecyclerView.Adapter<EstimateViewHolder> implements
    EstimateViewHolder.OnCancelBtnClickListener,
    EstimateViewHolder.OnChatBtnClickListener{

    List<Estimate> items = new ArrayList<>();


    public void add(Estimate e){
        items.add(e);
        notifyDataSetChanged();
    }

    @Override
    public EstimateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_estimate,parent,false);
        EstimateViewHolder holder = new EstimateViewHolder(view);
        holder.setOnChatBtnClickListener(this);
        holder.setOnCancelBtnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(EstimateViewHolder holder, int position) {
        holder.setEstimate(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
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
