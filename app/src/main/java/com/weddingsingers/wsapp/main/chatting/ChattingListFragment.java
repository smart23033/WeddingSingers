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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.ChatContract;
import com.weddingsingers.wsapp.function.chatting.chatting.ChattingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChattingListFragment extends Fragment {

    @BindView(R.id.chatting_list_lv_list)
    ListView listView;

    SimpleCursorAdapter mAdapter;

    public ChattingListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] from = {ChatContract.ChatUser.COLUMN_NAME, ChatContract.ChatUser.COLUMN_LAST_DATE, ChatContract.ChatMessage.COLUMN_MESSAGE};
        int[] to = {R.id.view_chat_user_tv_name, R.id.view_chat_user_tv_last_date, R.id.view_chat_user_tv_last_message};

        mAdapter = new SimpleCursorAdapter(getContext(), R.layout.view_chat_user, null, from, to, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chatting_list, container, false);

        ButterKnife.bind(this, view);

        listView.setAdapter(mAdapter);

        initData();

        return view;
    }

    public void initData() {

    }

    @OnItemClick(R.id.chatting_list_lv_list)
    public void onItemClick(int position, long id) {
        startActivity(new Intent(getActivity(), ChattingActivity.class));
    }
}
