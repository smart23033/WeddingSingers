package com.weddingsingers.wsapp.data.viewholder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.ScheduleList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class ScheduleListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.view_large_profile_riv_profile)
    RoundedImageView customerImageView;

    @BindView(R.id.view_large_profile_tv_user_name)
    TextView customerNameView;

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

    public interface OnCancelBtnClickListener {
        public void onCancelBtnClick(View view, ScheduleList scheduleList, int position);
    }

    OnCancelBtnClickListener cancelBtnListener;
    public void setOnCancelBtnClickListener(OnCancelBtnClickListener cancelBtnListener) {
        this.cancelBtnListener = cancelBtnListener;
    }


    public interface OnChatBtnClickListener {
        public void onChatBtnClick(View view, ScheduleList scheduleList, int position);
    }

    OnChatBtnClickListener chatBtnListener;
    public void setOnChatBtnClickListener(OnChatBtnClickListener chatBtnListener) {
        this.chatBtnListener = chatBtnListener;
    }


    public ScheduleListViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);


        customerImageView.mutateBackground(true);
        customerImageView.setOval(true);
        customerImageView.setBackgroundColor(Color.LTGRAY);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cancelBtnListener != null) {
                    cancelBtnListener.onCancelBtnClick(view, scheduleList, getAdapterPosition());
                }
            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chatBtnListener != null) {
                    chatBtnListener.onChatBtnClick(view, scheduleList, getAdapterPosition());
                }
            }
        });

    }

    ScheduleList scheduleList;

    public void setScheduleList(ScheduleList scheduleList) {
        this.scheduleList = scheduleList;
        customerNameView.setText(scheduleList.getCustomerImage());
        locationView.setText(scheduleList.getLocation());
        dateView.setText(scheduleList.getDate());
        songsView.setText(scheduleList.getSongs());
        specialView.setText(scheduleList.getSpecial());
//        standardView.setText(scheduleList.getStandard());
    }

}
