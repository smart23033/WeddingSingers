package com.weddingsingers.wsapp.data.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.LargeProfile;
import com.weddingsingers.wsapp.data.view.LargeProfileView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class ScheduleListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.view_detail_schedule_lpv_profile)
    LargeProfileView profileView;

    @BindView(R.id.view_detail_schedule_btn_cancel)
    Button cancelBtn;

    @BindView(R.id.view_detail_schedule_btn_chat)
    Button chatBtn;

    public interface OnCancelBtnClickListener {
        public void onCancelBtnClick(View view, LargeProfile profile, int position);
    }

    OnCancelBtnClickListener cancelBtnListener;
    public void setOnCancelBtnClickListener(OnCancelBtnClickListener cancelBtnListener) {
        this.cancelBtnListener = cancelBtnListener;
    }


    public interface OnChatBtnClickListener {
        public void onChatBtnClick(View view, LargeProfile profile, int position);
    }

    OnChatBtnClickListener chatBtnListener;
    public void setOnChatBtnClickListener(OnChatBtnClickListener chatBtnListener) {
        this.chatBtnListener = chatBtnListener;
    }


    public ScheduleListViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cancelBtnListener != null) {
                    cancelBtnListener.onCancelBtnClick(view, profile, getAdapterPosition());
                }
            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chatBtnListener != null) {
                    chatBtnListener.onChatBtnClick(view, profile, getAdapterPosition());
                }
            }
        });

    }

    LargeProfile profile;

    public void setScheduleList(LargeProfile profile) {
        this.profile = profile;
        profileView.setLargeProfile(profile);
    }

}
