package com.weddingsingers.wsapp.main.reservationmgm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SearchResult;
import com.weddingsingers.wsapp.data.SingerList;
import com.weddingsingers.wsapp.data.viewholder.SearchResultViewHolder;
import com.weddingsingers.wsapp.data.viewholder.SingerListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class ReservationListAdapter extends RecyclerView.Adapter<SingerListViewHolder>
    implements SingerListViewHolder.OnPayBtnClickListener{
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

    OnAdapterPayBtnClickListener listener;
    public void setOnAdapterPayBtnClickListener(OnAdapterPayBtnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onPayBtnClick(View view, SingerList singerList, int position) {
        if(listener != null){
            listener.onAdapterPayBtnClick(view,singerList,position);
        }

    }
}