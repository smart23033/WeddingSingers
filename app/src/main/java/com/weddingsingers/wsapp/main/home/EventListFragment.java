package com.weddingsingers.wsapp.main.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.EventList;
import com.weddingsingers.wsapp.function.event.event.EventActivity;
import com.weddingsingers.wsapp.function.video.video.VideoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";
    private String message;
    private static EventListFragment instance;

    @BindView(R.id.event_list_rv_list)
    RecyclerView recyclerView;

    EventListAdapter mAdapter;

    public EventListFragment() {
        // Required empty public constructor
    }

    public static EventListFragment newInstance(String message) {
        EventListFragment fragment = new EventListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        ButterKnife.bind(this, view);
        mAdapter = new EventListAdapter();
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnAdapterItemClickListener(new EventListAdapter.OnAdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, EventList eventList, int position) {
                Intent intent = new Intent(getContext(), EventActivity.class);
                //intent.putExtra(VideoActivity.EXTRA_SEARCH_RESULT, eventList);

                startActivity(intent);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        initData();

        return view;
    }

    private void initData(){
        for(int i = 0; i < 20; i++){
            EventList eventList = new EventList();
            //eventList.setThumbnail(ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher));
            eventList.setTitle("Event title " + i);
            eventList.setDate("2016. 4. 24");
            mAdapter.add(eventList);
        }
    }
}