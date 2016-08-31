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
public class CustomerListViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.view_reserved_customer_ev_profile)
    EstimateView estimateView;

    @BindView(R.id.view_reserved_customer_btn_chat)
    Button chatBtn;

    @BindView(R.id.view_reserved_customer_btn_response)
    Button responseBtn;

    public interface OnResponseBtnClickListener {
        public void onResponseBtnClick(View view, Estimate profile, int position);
    }

    OnResponseBtnClickListener responseBtnListener;
    public void setOnCancelBtnClickListener(OnResponseBtnClickListener responseBtnListener) {
        this.responseBtnListener = responseBtnListener;
    }


    public interface OnChatBtnClickListener {
        public void onChatBtnClick(View view, Estimate profile, int position);
    }

    OnChatBtnClickListener chatBtnListener;
    public void setOnChatBtnClickListener(OnChatBtnClickListener chatBtnListener) {
        this.chatBtnListener = chatBtnListener;
    }


    public CustomerListViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);

        responseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (responseBtnListener != null) {
                    responseBtnListener.onResponseBtnClick(view, estimate, getAdapterPosition());
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

    public void setCustomerList(Estimate estimate) {
        this.estimate = estimate;
        estimateView.setEstimate(estimate);
    }

}
