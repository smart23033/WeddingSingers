package com.weddingsingers.wsapp.data.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Singer;
import com.weddingsingers.wsapp.data.SingerList;
import com.weddingsingers.wsapp.data.VideoList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class ReservedSingerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.view_reserved_singer_riv_profile)
    RoundedImageView singerImageView;

    @BindView(R.id.view_reserved_singer_tv_singer_name)
    TextView singerNameView;

    @BindView(R.id.view_reserved_singer_tv_detail_location)
    TextView locationView;

    @BindView(R.id.view_reserved_singer_tv_detail_date)
    TextView dateView;

    @BindView(R.id.view_reserved_singer_tv_detail_songs)
    TextView songsView;

    public interface OnPayBtnClickListener {
        public void onPayBtnClick(View view, SingerList singerList, int position);
    }


    OnPayBtnClickListener listener;
    public void setOnPayBtnClickListener(OnPayBtnClickListener listener) {
        this.listener = listener;
    }




    public ReservedSingerViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onPayBtnClick(view, singerList, getAdapterPosition());
                }
            }
        });
    }

    SingerList singerList;

    public void setSingerList(SingerList singerList){
        this.singerList = singerList;
        singerNameView.setText(singerList.getSingerName());
        locationView.setText(singerList.getLocation());
        dateView.setText(singerList.getDate());
        songsView.setText(singerList.getSongs());
    }
}
