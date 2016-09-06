package com.weddingsingers.wsapp.data.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.view.EstimateView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class ScheduleListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.view_detail_schedule_ev_profile)
    EstimateView estimateView;

    @BindView(R.id.view_detail_schedule_btn_cancel)
    Button cancelBtn;

    @BindView(R.id.view_detail_schedule_btn_chat)
    Button chatBtn;

    public interface OnCancelBtnClickListener {
        public void onCancelBtnClick(View view, Estimate estimate, int position);
    }

    OnCancelBtnClickListener cancelBtnListener;
    public void setOnCancelBtnClickListener(OnCancelBtnClickListener cancelBtnListener) {
        this.cancelBtnListener = cancelBtnListener;
    }


    public interface OnChatBtnClickListener {
        public void onChatBtnClick(View view, Estimate estimate, int position);
    }

    OnChatBtnClickListener chatBtnListener;
    public void setOnChatBtnClickListener(OnChatBtnClickListener chatBtnListener) {
        this.chatBtnListener = chatBtnListener;
    }


    public ScheduleListViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cancelBtnListener != null) {
                    cancelBtnListener.onCancelBtnClick(view, estimate, getAdapterPosition());
                }
            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chatBtnListener != null) {
                    chatBtnListener.onChatBtnClick(view, estimate, getAdapterPosition());
                }
            }
        });

    }

    Estimate estimate;

    public void setScheduleList(Estimate estimate) {
        this.estimate = estimate;
        estimateView.setEstimate(estimate);
    }

}
