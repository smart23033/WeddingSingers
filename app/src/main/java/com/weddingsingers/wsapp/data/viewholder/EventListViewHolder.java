package com.weddingsingers.wsapp.data.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.EventList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by overboy00 on 2016-08-26.
 */
public class EventListViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.view_event_list_iv_event)
    ImageView thumbnailImageView;

    @BindView(R.id.view_event_list_tv_event)
    TextView titleView;

    @BindView(R.id.view_event_list_tv_date)
    TextView dateView;

    public interface OnEventListItemClickListener {
        public void onEventListItemClick(View view, EventList eventList, int position);
    }

    OnEventListItemClickListener listener;
    public void setOnEventListItemClickListener(OnEventListItemClickListener listener) {
        this.listener = listener;
    }

    public EventListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onEventListItemClick(view, eventList, getAdapterPosition());
                }
            }
        });
    }

    EventList eventList;

    public void setEventList(EventList eventList) {
        this.eventList = eventList;
        //thumbnailImageView.setImageBitmap(eventList.getThumbnail());
        titleView.setText(eventList.getTitle());
        dateView.setText(eventList.getDate());
    }
}