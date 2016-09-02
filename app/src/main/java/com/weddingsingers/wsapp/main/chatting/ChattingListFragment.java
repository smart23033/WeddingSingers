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

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.ChatContract;
import com.weddingsingers.wsapp.data.ChattingList;
import com.weddingsingers.wsapp.data.Review;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.data.VideoList;
import com.weddingsingers.wsapp.function.chatting.chatting.ChattingActivity;
import com.weddingsingers.wsapp.function.video.singerreview.SingerReviewAdapter;
import com.weddingsingers.wsapp.function.video.video.VideoActivity;
import com.weddingsingers.wsapp.main.home.VideoListAdapter;
import com.weddingsingers.wsapp.manager.DBManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChattingListFragment extends Fragment {

    @BindView(R.id.chatting_list_rv_list)
    RecyclerView recyclerView;

    ChattingListAdapter mAdapter;

    public ChattingListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chatting_list, container, false);

        ButterKnife.bind(this, view);

        mAdapter = new ChattingListAdapter();
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnAdapterItemClickListener(new ChattingListAdapter.OnAdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, ChattingList chattingList, int position) {
                Intent intent = new Intent(getContext(), ChattingActivity.class);
                //intent.putExtra(ChattingList.EXTRA_SEARCH_RESULT, chattingList);

                startActivity(intent);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        initData();

        return view;
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            ChattingList chattingList = new ChattingList();
            //videoList.setThumbnail(ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher));
            chattingList.setName("사용자 " + i);
            chattingList.setMsg("이분 채소 아" + i + "유");
//            review.setDate("2016. 8. 24");
            mAdapter.add(chattingList);

        }
    }
}


/*    @OnItemClick(R.id.chatting_list_rv_list)
    public void onItemClick(int position, long id) {
        Cursor cursor = (Cursor)recyclerView.getItemAtPosition(position);
        User user = new User();
        user.setId(cursor.getLong(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_SERVER_ID)));
        user.setName(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_NAME)));
        Intent intent = new Intent(getContext(), ChattingActivity.class);
        //intent.putExtra(ChattingActivity.EXTRA_USER, user);
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
    }*/
