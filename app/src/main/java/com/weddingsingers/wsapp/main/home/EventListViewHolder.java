package com.weddingsingers.wsapp.main.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.weddingsingers.wsapp.R;

/**
 * Created by overboy00 on 2016-08-26.
 */
public class EventListViewHolder extends RecyclerView.ViewHolder{
    TextView textView;

    public EventListViewHolder(View itemView) {
        super(itemView);
        textView = (TextView)itemView.findViewById(R.id.event_list_tv_name);
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
