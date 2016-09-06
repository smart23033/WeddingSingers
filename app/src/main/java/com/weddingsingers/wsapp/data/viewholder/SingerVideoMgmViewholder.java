package com.weddingsingers.wsapp.data.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SingerVideoMgm;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class SingerVideoMgmViewholder extends RecyclerView.ViewHolder {

    @BindView(R.id.view_singer_video_mgm_tv_title)
    TextView titleView;

    @BindView(R.id.view_singer_video_mgm_tv_date)
    TextView dateView;

    @BindView(R.id.view_singer_video_mgm_tv_hit)
    TextView hitView;

    @BindView(R.id.view_singer_video_mgm_tv_favorite)
    TextView favoriteView;

    @BindView(R.id.view_singer_video_mgm_cb)
    public CheckBox checkBox;

    public SingerVideoMgmViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        //checkBox = (CheckBox) itemView.findViewById(R.id.view_singer_video_mgm_cb);

    }

    SingerVideoMgm singerVideoMgm;

    public void setSingerVideoMgm(SingerVideoMgm singerVideoMgm) {
        this.singerVideoMgm = singerVideoMgm;
        titleView.setText(singerVideoMgm.getTitle());
        dateView.setText(singerVideoMgm.getDate());
        hitView.setText("" + singerVideoMgm.getHit());
        favoriteView.setText("" + singerVideoMgm.getFavorite());
    }
}
