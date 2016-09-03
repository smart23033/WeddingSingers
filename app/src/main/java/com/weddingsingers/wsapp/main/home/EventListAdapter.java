package com.weddingsingers.wsapp.main.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.EventList;
import com.weddingsingers.wsapp.data.viewholder.EventListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SJSJ on 2016-07-28.
 */
public class EventListAdapter extends RecyclerView.Adapter<EventListViewHolder>
        implements EventListViewHolder.OnEventListItemClickListener{

    List<EventList> items = new ArrayList<>();

    public void add(EventList eventList) {
        items.add(eventList);
        notifyDataSetChanged();
    }


    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public EventListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_event_list, parent, false);
        EventListViewHolder holder = new EventListViewHolder(view);
        holder.setOnEventListItemClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(EventListViewHolder holder, int position) {
        holder.setEventList(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public interface OnAdapterItemClickListener{
        public void onAdapterItemClick(View view, EventList eventList, int position);
    }

    OnAdapterItemClickListener listener;
    public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onEventListItemClick(View view, EventList eventList, int position) {
        if(listener != null){
            listener.onAdapterItemClick(view, eventList, position);
        }
    }
}
