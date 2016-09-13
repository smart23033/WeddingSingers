package com.weddingsingers.wsapp.main.chatting;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.ChatContract;
import com.weddingsingers.wsapp.data.ChattingList;
import com.weddingsingers.wsapp.manager.DBManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChattingListFragment extends Fragment {

    @BindView(R.id.chatting_list_rv_list)
    RecyclerView recyclerView;

    ChattingListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatting_list, container, false);

        ButterKnife.bind(this, view);

        mAdapter = new ChattingListAdapter();

        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        initData();

        return view;
    }

    ChattingList data;
    ArrayList<ChattingList> list;
    private void initData() {
        Cursor cursor = DBManager.getInstance().getChatUser();
        if (cursor != null){
            list = new ArrayList<>();

            while (cursor.moveToNext()){
                data = new ChattingList();
                data.setName(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_NAME)));
                data.setThumbnail(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_PROFILE_IMAGE)));
                data.setMsg(cursor.getString(cursor.getColumnIndex(ChatContract.ChatMessage.COLUMN_MESSAGE)));
                data.setdTime(cursor.getString(cursor.getColumnIndex(ChatContract.ChatMessage.COLUMN_CREATED)));
                list.add(data);
            }

            int count = 0;
            while (count < (10 - cursor.getCount())) {
                data = new ChattingList();
                data.setMsg("채팅 목록이 없습니다.");
                list.add(data);
                count++;
            }
            mAdapter.setData(list);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
