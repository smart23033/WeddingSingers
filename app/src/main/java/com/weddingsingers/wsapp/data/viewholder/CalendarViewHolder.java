package com.weddingsingers.wsapp.data.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.CalendarList;
import com.weddingsingers.wsapp.data.EventList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class CalendarViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.view_calendar_tv_month)
    TextView monthView;

    public interface OnCalendarListItemClickListener {
        public void onCalendarListItemClick(View view, CalendarList calendarList, int position);
    }

    OnCalendarListItemClickListener listener;
    public void setOnCalendarListItemClickListener(OnCalendarListItemClickListener listener) {
        this.listener = listener;
    }

    public CalendarViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onCalendarListItemClick(view, calendarList, getAdapterPosition());
                }
            }
        });
    }

    CalendarList calendarList;

    public void setCalendarList(CalendarList calendarList) {
        this.calendarList = calendarList;
        monthView.setText("" + calendarList.getMonth());
    }

}
