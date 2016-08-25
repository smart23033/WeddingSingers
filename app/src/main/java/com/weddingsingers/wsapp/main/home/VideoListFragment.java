package com.weddingsingers.wsapp.main.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoListFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";
    private String message;
    private static VideoListFragment instance;

    RecyclerView listView;
    MainAdapter mAdapter;

    public VideoListFragment() {
        // Required empty public constructor
    }

    public static VideoListFragment getInstance(String message) {
        VideoListFragment fragment = new VideoListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_list, container, false);
        listView = (RecyclerView)view.findViewById(R.id.rv_list);
        mAdapter = new MainAdapter();
        listView.setAdapter(mAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(manager);

        initData();

        return view;
    }

    private void initData(){
        String item = "item 1";
        mAdapter.add(item);
    }

}

