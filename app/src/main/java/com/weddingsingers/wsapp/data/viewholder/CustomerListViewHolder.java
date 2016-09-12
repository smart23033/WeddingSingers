package com.weddingsingers.wsapp.data.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class CustomerListViewHolder extends RecyclerView.ViewHolder{

    private final static int TYPE_WAIT = 20;
    private final static int TYPE_ACCEPT = 30;
    private final static int TYPE_REJECT = 21;
    private final static int TYPE_CANCEL = 31;

    Context context = MyApplication.getContext();

    @BindView(R.id.view_reserved_customer_iv_color)
    ImageView statusView;

    @BindView(R.id.view_reserved_customer_riv_profile)
    RoundedImageView customerImageView;

    @BindView(R.id.view_reserved_customer_tv_customer_name)
    TextView customerNameView;

    @BindView(R.id.view_reserved_customer_tv_detail_location)
    TextView locationView;

    @BindView(R.id.view_reserved_customer_tv_detail_date)
    TextView dateView;

    @BindView(R.id.view_reserved_customer_tv_detail_songs)
    TextView songsView;

    @BindView(R.id.view_reserved_customer_btn_accept)
    Button acceptBtn;

    @BindView(R.id.view_reserved_customer_btn_cancel)
    Button cancelBtn;

    @BindView(R.id.view_reserved_customer_btn_chat)
    Button chatBtn;

    @BindView(R.id.view_reserved_customer_tv_notification)
    TextView notificationView;

    public interface OnAcceptBtnClickListener {
        public void onAcceptBtnClick(View view, Estimate estimate, int position);
    }

    OnAcceptBtnClickListener acceptBtnListener;

    public void setOnAcceptBtnClickListener(OnAcceptBtnClickListener acceptBtnkListener) {
        this.acceptBtnListener = acceptBtnkListener;
    }

    public interface OnChatBtnClickListener {
        public void onChatBtnClick(View view, Estimate estimate, int position);
    }

    OnChatBtnClickListener chatBtnListener;

    public void setOnChatBtnClickListener(OnChatBtnClickListener chatBtnListener) {
        this.chatBtnListener = chatBtnListener;
    }


    public interface OnCancelBtnClickListener {
        public void onCancelBtnClick(View view, Estimate estimate, int position);
    }

    OnCancelBtnClickListener cancelBtnListener;

    public void setOnCancelBtnClickListener(OnCancelBtnClickListener cancelBtnListener) {
        this.cancelBtnListener = cancelBtnListener;
    }


    public CustomerListViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        customerImageView.mutateBackground(true);
        customerImageView.setOval(true);
        customerImageView.setBackgroundColor(Color.LTGRAY);


        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (acceptBtnListener != null) {
                    acceptBtnListener.onAcceptBtnClick(view, estimate, getAdapterPosition());
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

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cancelBtnListener != null) {
                    cancelBtnListener.onCancelBtnClick(view, estimate, getAdapterPosition());
                }
            }
        });


    }

    Estimate estimate;

    public void setCustomerList(Estimate estimate) {
        this.estimate = estimate;
        Glide.with(context)
                .load(estimate.getSingerImage())
                .centerCrop()
                .crossFade()
                .error(ContextCompat.getDrawable(context, R.drawable.view_profile_ic_person))
                .into(customerImageView);
        customerNameView.setText(estimate.getSingerName());
        locationView.setText(estimate.getLocation());
        dateView.setText(estimate.getDate());
        songsView.setText(estimate.getSongs());


        int status = estimate.getStatus();

        switch (status){
//            결제완료
            case TYPE_ACCEPT:{
                statusView.setBackgroundColor(context.getResources().getColor(R.color.colorAccept));

                cancelBtn.setVisibility(View.VISIBLE);
                chatBtn.setVisibility(View.VISIBLE);
                acceptBtn.setVisibility(View.GONE);
                notificationView.setVisibility(View.GONE);
                break;
            }
//            응답대기
            case TYPE_WAIT: {
                statusView.setBackgroundColor(context.getResources().getColor(R.color.colorWait));

                cancelBtn.setVisibility(View.VISIBLE);
                chatBtn.setVisibility(View.VISIBLE);
                acceptBtn.setVisibility(View.VISIBLE);
                notificationView.setVisibility(View.GONE);
                break;
            }
            case TYPE_REJECT:
            case TYPE_CANCEL:
            {
                statusView.setBackgroundColor(context.getResources().getColor(R.color.colorReject));

                cancelBtn.setVisibility(View.GONE);
                chatBtn.setVisibility(View.GONE);
                acceptBtn.setVisibility(View.GONE);
                notificationView.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

}
