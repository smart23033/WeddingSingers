package com.weddingsingers.wsapp.data.viewholder;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
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
import butterknife.OnClick;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class SingerListViewHolder extends RecyclerView.ViewHolder {

    private final static int TYPE_WAIT = 10;
    private final static int TYPE_ACCEPT = 20;
    private final static int TYPE_REJECT = 11;

    Context context = MyApplication.getContext();

    @BindView(R.id.view_reserved_singer_iv_color)
    ImageView statusView;

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

    @BindView(R.id.view_reserved_singer_btn_pay)
    Button payBtn;

    @BindView(R.id.view_reserved_singer_btn_cancel)
    Button cancelBtn;

    @BindView(R.id.view_reserved_singer_btn_chat)
    Button chatBtn;

    public interface OnPayBtnClickListener {
        public void onPayBtnClick(View view, Estimate estimate, int position);
    }

    OnPayBtnClickListener payBtnListener;
    public void setOnPayBtnClickListener(OnPayBtnClickListener payBtnListener) {
        this.payBtnListener = payBtnListener;
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


    public SingerListViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        singerImageView.mutateBackground(true);
        singerImageView.setOval(true);
        singerImageView.setBackgroundColor(Color.LTGRAY);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (payBtnListener != null) {
                    payBtnListener.onPayBtnClick(view, estimate, getAdapterPosition());
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

    @TargetApi(Build.VERSION_CODES.M)
    public void setSingerList(Estimate estimate){
        this.estimate = estimate;
        Glide.with(context)
                .load(estimate.getSingerImage())
                .centerCrop()
                .crossFade()
                .error(ContextCompat.getDrawable(context, R.drawable.view_profile_ic_person))
                .into(singerImageView);
        singerNameView.setText(estimate.getSingerName());
        locationView.setText(estimate.getLocation());
        dateView.setText(estimate.getDate());
        songsView.setText(estimate.getSongs());

        int status = estimate.getStatus();
        if (status == TYPE_WAIT) {
            statusView.setBackgroundColor(context.getResources().getColor(R.color.colorWait));
        }else if(status == TYPE_ACCEPT){
            statusView.setBackgroundColor(context.getResources().getColor(R.color.colorAccept));
        }else if(status == TYPE_REJECT){
            statusView.setBackgroundColor(context.getResources().getColor(R.color.colorReject));
        }
    }
}
