package com.weddingsingers.wsapp.data.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class SingerViewHolder extends RecyclerView.ViewHolder{
    ImageView  imageView;
    TextView titleTextView;
    TextView singerNameTextView;
    TextView dateTextView;
    TextView hitTextView;
    TextView favoriteTextView;

    public SingerViewHolder(View itemView) {
        super(itemView);
    }
}
