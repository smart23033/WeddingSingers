package com.weddingsingers.wsapp.main.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.weddingsingers.wsapp.R;

/**
 * Created by SJSJ on 2016-07-28.
 */
public class VideoListViewHolder extends RecyclerView.ViewHolder{
    TextView textView;

    public VideoListViewHolder(View itemView) {
        super(itemView);
        textView = (TextView)itemView.findViewById(R.id.video_list_tv_name);
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
