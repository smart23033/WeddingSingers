package com.weddingsingers.wsapp.data.viewholder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Alarm;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-21.
 */
public class AlarmListViewHolder extends RecyclerView.ViewHolder {

    Context context = MyApplication.getContext();

    @BindView(R.id.view_alarm_riv_picture)
    RoundedImageView userImageView;

    @BindView(R.id.view_alarm_tv_name)
    TextView nameView;

    @BindView(R.id.view_alarm_tv_message)
    TextView messageView;

    public AlarmListViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        userImageView.mutateBackground(true);
        userImageView.setOval(true);

    }

    Alarm alarm;

    public void setAlarm(Alarm alarm) {

        this.alarm = alarm;

        Glide.with(context)
                .load(alarm.getUserImage())
                .centerCrop()
                .crossFade()
                .error(ContextCompat.getDrawable(context, R.drawable.view_profile_ic_person))
                .into(userImageView);

        nameView.setText(alarm.getUserName());
        messageView.setText(alarm.getMessage());

    }

    public interface OnItemClickListener {
        public void onItemClick(View view, Alarm alarm, int position);
    }

    OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
