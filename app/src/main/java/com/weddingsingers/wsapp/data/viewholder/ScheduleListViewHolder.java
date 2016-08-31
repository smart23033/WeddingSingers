package com.weddingsingers.wsapp.data.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;

import butterknife.BindView;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class ScheduleListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.view_large_profile_riv_profile)
    RoundedImageView customerImageView;

    @BindView(R.id.view_large_profile_tv_user_name)
    TextView userNameView;

    @BindView(R.id.view_large_profile_tv_detail_location)
    TextView locationView;

    @BindView(R.id.view_large_profile_tv_detail_date)
    TextView dateView;

    @BindView(R.id.view_large_profile_tv_detail_songs)
    TextView songsView;

    @BindView(R.id.view_large_profile_tv_special)
    TextView specialView;

    @BindView(R.id.view_large_profile_tv_standard)
    TextView standardView;

    @BindView(R.id.view_detail_schedule_btn_cancel)
    Button cancelBtn;

    @BindView(R.id.view_detail_schedule_btn_chat)
    Button chatBtn;


    public ScheduleListViewHolder(View itemView) {
        super(itemView);
    }

}
