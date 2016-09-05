package com.weddingsingers.wsapp.main.chatting;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.ChattingList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class ChattingListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.view_chatting_list_riv_picture)
    RoundedImageView pictureView;

    @BindView(R.id.view_chatting_list_tv_name)
    TextView nameView;

    @BindView(R.id.view_chatting_list_tv_last_msg)
    TextView msgView;

    public interface OnChattingListItemClickListener {
        public void onChattingListItemClick(View view, ChattingList chattingList, int position);
    }

    OnChattingListItemClickListener listener;
    public void setOnChattingListItemClickListener(OnChattingListItemClickListener listener) {
        this.listener = listener;
    }

    public ChattingListViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onChattingListItemClick(view, chattingList, getAdapterPosition());
                }
            }
        });
    }

    ChattingList chattingList;

    public void setChattingList(ChattingList chattingList) {
        this.chattingList= chattingList;
        //pictureView.setImageBitmap(chattingList.getThumbnail());
        nameView.setText(chattingList.getName());
        msgView.setText(chattingList.getMsg());
    }

}
