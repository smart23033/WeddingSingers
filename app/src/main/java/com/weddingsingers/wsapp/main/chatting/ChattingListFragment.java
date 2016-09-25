package com.weddingsingers.wsapp.main.chatting;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.ChatContract;
import com.weddingsingers.wsapp.data.ChattingList;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.function.chatting.chatting.ChattingActivity;
import com.weddingsingers.wsapp.manager.DBManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChattingListFragment extends Fragment {

    @BindView(R.id.chatting_list_rv_list)
    RecyclerView recyclerView;

    @BindView(R.id.chatting_list_lv_list)
    ListView listView;

    SimpleCursorAdapter mAdapter;
    ChattingListAdapter cAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] from = {ChatContract.ChatUser.COLUMN_NAME, ChatContract.ChatMessage.COLUMN_MESSAGE};
        int[] to = {R.id.view_chat_user_tv_name, R.id.view_chat_user_tv_last_message};
        mAdapter = new SimpleCursorAdapter(getContext(), R.layout.view_chat_user, null, from, to, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatting_list, container, false);

        ButterKnife.bind(this, view);

        cAdapter = new ChattingListAdapter();

        recyclerView.setAdapter(cAdapter);
        listView.setAdapter(mAdapter);

//        LinearLayoutManager manager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(manager);
//
        initData();

        return view;
    }

    @OnItemClick(R.id.chatting_list_lv_list)
    public void onItemClick(int position, long id) {
        Cursor cursor = (Cursor) listView.getItemAtPosition(position);
        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_SERVER_ID)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_EMAIL)));
        user.setPhotoURL(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_IMAGE)));
        user.setName(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_NAME)));
        Intent intent = new Intent(getContext(), ChattingActivity.class);
        intent.putExtra(ChattingActivity.EXTRA_USER, user);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        Cursor c = DBManager.getInstance().getChatUser();
        mAdapter.changeCursor(c);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.changeCursor(null);
    }

    ChattingList data;
    ArrayList<ChattingList> list;

    private void initData() {
        Cursor cursor = DBManager.getInstance().getChatUser();
        if (cursor != null) {
            list = new ArrayList<>();

            while (cursor.moveToNext()) {
                data = new ChattingList();
                Log.i("CHATTIG_LIST - COLUMN_NAME - ", cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_NAME)));
                Log.i("CHATTIG_LIST - COLUMN_SERVER_ID - ", cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_SERVER_ID)));
                Log.i("CHATTIG_LIST - COLUMN_EMAIL - ", cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_EMAIL)));
                Log.i("CHATTIG_LIST - COLUMN_MESSAGE - ", cursor.getString(cursor.getColumnIndex(ChatContract.ChatMessage.COLUMN_MESSAGE)));
                Log.i("CHATTIG_LIST - COLUMN_CREATED - ", cursor.getString(cursor.getColumnIndex(ChatContract.ChatMessage.COLUMN_CREATED)));
                Log.i("CHATTIG_LIST - COLUMN_TYPE - ", cursor.getString(cursor.getColumnIndex(ChatContract.ChatMessage.COLUMN_TYPE)));
                Log.i("CHATTIG_LIST - COLUMN_USER_ID - ", cursor.getString(cursor.getColumnIndex(ChatContract.ChatMessage.COLUMN_USER_ID)));
//                data.setName(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_NAME)));
//                data.setMsg(cursor.getString(cursor.getColumnIndex(ChatContract.ChatMessage.COLUMN_MESSAGE)));
//                data.setdTime(cursor.getString(cursor.getColumnIndex(ChatContract.ChatMessage.COLUMN_CREATED)));
//                list.add(data);
            }

            int count = 0;
            while (count < (10 - cursor.getCount())) {
                data = new ChattingList();
                data.setMsg("채팅 목록이 없습니다.");
                list.add(data);
                count++;
            }
            //cAdapter.setData(list);
        }

    }
}
