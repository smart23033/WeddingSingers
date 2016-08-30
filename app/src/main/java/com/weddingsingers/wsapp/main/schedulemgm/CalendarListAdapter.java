package com.weddingsingers.wsapp.main.schedulemgm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.CalendarList;
import com.weddingsingers.wsapp.data.viewholder.CalendarViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class CalendarListAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    List<CalendarList> items = new ArrayList<>();

    public void add(CalendarList c){
        items.add(c);
        notifyDataSetChanged();
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_calendar,parent,false);

        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CalendarViewHolder holder, int position) {
        holder.setCalendarList(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
