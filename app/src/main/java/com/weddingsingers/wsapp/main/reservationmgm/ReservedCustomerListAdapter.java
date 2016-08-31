package com.weddingsingers.wsapp.main.reservationmgm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.viewholder.CustomerListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class ReservedCustomerListAdapter extends RecyclerView.Adapter<CustomerListViewHolder> implements
    CustomerListViewHolder.OnChatBtnClickListener,
    CustomerListViewHolder.OnResponseBtnClickListener{

    List<Estimate> items = new ArrayList<>();

    public void add(Estimate p){
        items.add(p);
        notifyDataSetChanged();
    }


    @Override
    public CustomerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_reserved_customer,parent,false);
        CustomerListViewHolder holder = new CustomerListViewHolder(view);
        holder.setOnCancelBtnClickListener(this);
        holder.setOnChatBtnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomerListViewHolder holder, int position) {
        holder.setCustomerList(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnAdapterChatBtnClickListener {
        public void onAdapterChatBtnClick(View view, Estimate estimate, int position);
    }


    OnAdapterChatBtnClickListener chatBtnListener;
    public void setOnAdapterChatBtnClickListener(OnAdapterChatBtnClickListener chatBtnListener){
        this.chatBtnListener = chatBtnListener;
    }

    public interface OnAdapterResponseBtnClickListener{
        public void onAdapterResponseBtnClick(View view, Estimate profile, int position);
    }

    OnAdapterResponseBtnClickListener responseBtnClickListener;
    public void setOnAdapterResponseBtnClickListener(OnAdapterResponseBtnClickListener responseBtnClickListener){
        this.responseBtnClickListener = responseBtnClickListener;
    }
    @Override
    public void onChatBtnClick(View view, Estimate estimate, int position) {
        if(chatBtnListener != null){
            chatBtnListener.onAdapterChatBtnClick(view,estimate,position);
        }
    }

    @Override
    public void onResponseBtnClick(View view, Estimate estimate, int position) {
        if(responseBtnClickListener != null){
            responseBtnClickListener.onAdapterResponseBtnClick(view,estimate,position);
        }
    }
}
