package com.weddingsingers.wsapp.data.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Singer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class SingerViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.view_singer_iv_thumbnail)
    ImageView imageView;

    @BindView(R.id.view_singer_tv_title)
    TextView titleTextView;

    @BindView(R.id.view_singer_tv_singer_name)
    TextView singerNameTextView;

    @BindView(R.id.view_singer_tv_date)
    TextView dateTextView;

    @BindView(R.id.view_singer_tv_hit)
    TextView hitTextView;

    @BindView(R.id.view_singer_tv_num_of_favorite)
    TextView favoriteTextView;

    public SingerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

//    과연 이게 싱어를 받아오는 것일까?
    Singer singer;

    public void setSinger(Singer singer){
        this.singer = singer;
    }

}
