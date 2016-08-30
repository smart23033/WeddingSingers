package com.weddingsingers.wsapp.data.viewholder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SingerList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class SingerListViewHolder extends RecyclerView.ViewHolder {

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
        public void onPayBtnClick(View view, SingerList singerList, int position);
    }

    OnPayBtnClickListener payBtnListener;
    public void setOnPayBtnClickListener(OnPayBtnClickListener payBtnListener) {
        this.payBtnListener = payBtnListener;
    }



    public interface OnChatBtnClickListener {
        public void onChatBtnClick(View view, SingerList singerList, int position);
    }

    OnChatBtnClickListener chatBtnListener;
    public void setOnChatBtnClickListener(OnChatBtnClickListener chatBtnListener) {
        this.chatBtnListener = chatBtnListener;
    }


    public interface OnCancelBtnClickListener {
        public void onCancelBtnClick(View view, SingerList singerList, int position);
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
                    payBtnListener.onPayBtnClick(view, singerList, getAdapterPosition());
                }
            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chatBtnListener != null) {
                    chatBtnListener.onChatBtnClick(view, singerList, getAdapterPosition());
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cancelBtnListener != null) {
                    cancelBtnListener.onCancelBtnClick(view, singerList, getAdapterPosition());
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
