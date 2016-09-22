package com.weddingsingers.wsapp.function.chatting.chatting;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.ChatContract;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.fcm.MyFirebaseMessagingService;
import com.weddingsingers.wsapp.fcm.MyGcmListenerService;
import com.weddingsingers.wsapp.function.video.video.VideoFragment;
import com.weddingsingers.wsapp.manager.DBManager;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.MessageSendRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.SCROLL_INDICATOR_BOTTOM;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChattingFragment extends Fragment {

    @BindView(R.id.chatting_rv_list)
    RecyclerView recyclerView;

    ChattingAdapter mAdapter;

    @BindView(R.id.chatting_et_input)
    EditText inputView;

    public static final String EXTRA_USER = "user";
    static User user;

    LocalBroadcastManager mLBM;

    public ChattingFragment() {
        // Required empty public constructor
    }

    public static ChattingFragment newInstance(User user) {
        ChattingFragment fragment = new ChattingFragment();
        ChattingFragment.user = user;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chatting, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new ChattingAdapter();

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mLBM = LocalBroadcastManager.getInstance(getContext());

        return view;
    }

    @OnClick(R.id.chatting_btn_send)
    public void onSend(View view) {
        final String message = inputView.getText().toString();
        MessageSendRequest request = new MessageSendRequest(getContext(), user, message);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<String>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                Toast.makeText(getActivity(), "SUCCESS" + result.getResult(), Toast.LENGTH_SHORT).show();
                DBManager.getInstance().addMessage(user, ChatContract.ChatMessage.TYPE_SEND, message);
                updateMessage();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getActivity(), "FAIL" + errorCode + "-" + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateMessage() {
        Cursor c = DBManager.getInstance().getChatMessage(user);
        mAdapter.changeCursor(c);
        recyclerView.scrollToPosition(c.getCount() - 1);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMessage();
        mLBM.registerReceiver(mReceiver, new IntentFilter(MyFirebaseMessagingService.ACTION_CHAT_MESSAGE));
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            User u = (User) intent.getSerializableExtra(MyFirebaseMessagingService.EXTRA_CHAT_USER);
            if (u.getId() == user.getId()) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateMessage();
                    }
                });
                intent.putExtra(MyFirebaseMessagingService.EXTRA_RESULT, true);
            }
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.changeCursor(null);
        mLBM.unregisterReceiver(mReceiver);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                getActivity().finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
