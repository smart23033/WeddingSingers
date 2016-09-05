package com.weddingsingers.wsapp.main.chatting;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.ChatContract;
import com.weddingsingers.wsapp.data.ChattingList;
import com.weddingsingers.wsapp.data.Review;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.function.chatting.chatting.ChattingActivity;
import com.weddingsingers.wsapp.manager.DBManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChattingListFragment extends Fragment {

//    @BindView(R.id.chatting_list_rv_list)
//    RecyclerView recyclerView;

    @BindView(R.id.chatting_list_lv_list)
    ListView listView;


    SimpleCursorAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] from = {ChatContract.ChatUser.COLUMN_NAME, ChatContract.ChatMessage.COLUMN_MESSAGE};
        int[] to = {R.id.view_chatting_list_tv_name, R.id.view_chatting_list_tv_last_msg};
        mAdapter = new SimpleCursorAdapter(getContext(), R.layout.view_chatting_list, null, from, to, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatting_list, container, false);
        ButterKnife.bind(this, view);
        listView.setAdapter(mAdapter);
        return view;
    }

    @OnItemClick(R.id.chatting_list_lv_list)
    public void onItemClick(int position, long id) {
        Cursor cursor = (Cursor)listView.getItemAtPosition(position);
        ChattingList chattingList = new ChattingList();
        chattingList.setId(cursor.getLong(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_SERVER_ID)));
        chattingList.setName(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_NAME)));
        chattingList.setMsg(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_LAST_MESSAGE)));
        Intent intent = new Intent(getContext(), ChattingActivity.class);
        intent.putExtra(ChattingActivity.EXTRA_USER, chattingList);
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
}
