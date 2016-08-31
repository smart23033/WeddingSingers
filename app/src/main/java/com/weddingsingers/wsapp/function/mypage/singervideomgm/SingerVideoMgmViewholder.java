package com.weddingsingers.wsapp.function.mypage.singervideomgm;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SingerVideoMgm;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class SingerVideoMgmViewholder extends RecyclerView.ViewHolder {

    Context context = MyApplication.getContext();

    @BindView(R.id.view_singer_video_mgm_tv_title)
    TextView titleView;

    @BindView(R.id.view_singer_video_mgm_tv_date)
    TextView dateView;

    @BindView(R.id.view_singer_video_mgm_tv_hit)
    TextView hitView;

    @BindView(R.id.view_singer_video_mgm_tv_favorite)
    TextView favoriteView;

    public SingerVideoMgmViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    @OnClick(R.id.view_singer_video_mgm_btn_modify)
    public void onModifyClick() {
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
