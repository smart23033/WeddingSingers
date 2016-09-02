package com.weddingsingers.wsapp.main.chatting;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.ChattingList;
import com.weddingsingers.wsapp.data.Review;
import com.weddingsingers.wsapp.data.VideoList;
import com.weddingsingers.wsapp.data.viewholder.SingerReviewViewHolder;
import com.weddingsingers.wsapp.data.viewholder.VideoListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class ChattingListAdapter extends RecyclerView.Adapter<ChattingListViewHolder>
        implements ChattingListViewHolder.OnChattingListItemClickListener {

    List<ChattingList> items = new ArrayList<>();

    public void add(ChattingList chattingList) {
        items.add(chattingList);
        notifyDataSetChanged();
    }

    @Override
    public ChattingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chatting_list, parent, false);
        ChattingListViewHolder holder = new ChattingListViewHolder(view);
        holder.setOnChattingListItemClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(ChattingListViewHolder holder, int position) {
        holder.setChattingList(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnAdapterItemClickListener{
        public void onAdapterItemClick(View view, ChattingList chattingList, int position);
    }

    OnAdapterItemClickListener listener;
    public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onChattingListItemClick(View view, ChattingList chattingList, int position) {
        if(listener != null){
            listener.onAdapterItemClick(view, chattingList, position);
        }
    }
}