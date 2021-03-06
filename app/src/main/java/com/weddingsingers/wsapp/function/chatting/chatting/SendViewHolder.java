package com.weddingsingers.wsapp.function.chatting.chatting;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-05.
 */
public class SendViewHolder  extends RecyclerView.ViewHolder {

    @BindView(R.id.view_chat_send_tv_msg)
    TextView messageView;

    public SendViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setMessage(String message) {
        messageView.setText(message);
    }
}
