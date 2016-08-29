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

    public interface OnPayBtnClickListener {
        public void onPayBtnClick(View view, SingerList singerList, int position);
    }


    OnPayBtnClickListener listener;
    public void setOnPayBtnClickListener(OnPayBtnClickListener listener) {
        this.listener = listener;
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
                if (listener != null) {
                    listener.onPayBtnClick(view, singerList, getAdapterPosition());
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
