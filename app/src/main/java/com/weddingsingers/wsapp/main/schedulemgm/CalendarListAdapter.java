package com.weddingsingers.wsapp.main.schedulemgm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.CalendarList;
import com.weddingsingers.wsapp.data.viewholder.CalendarListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class CalendarListAdapter extends RecyclerView.Adapter<CalendarListViewHolder> implements
    CalendarListViewHolder.OnCalendarListItemClickListener{
    List<CalendarList> items = new ArrayList<>();

    public void add(CalendarList c){
        items.add(c);
        notifyDataSetChanged();
    }

    @Override
    public CalendarListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_calendar,parent,false);
        CalendarListViewHolder holder = new CalendarListViewHolder(view);
        holder.setOnCalendarListItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(CalendarListViewHolder holder, int position) {
        holder.setCalendarList(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnAdapterItemClickListener{
        public void onAdapterItemClick(View view, CalendarList calendarList, int position);
    }

    OnAdapterItemClickListener listener;
    public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onCalendarListItemClick(View view, CalendarList calendarList, int position) {
        if(listener != null){
            listener.onAdapterItemClick(view,calendarList,position);
        }
    }
}
