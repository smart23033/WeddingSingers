package com.weddingsingers.wsapp.main.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.VideoList;
import com.weddingsingers.wsapp.function.video.video.VideoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoListFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";
    private String message;
    private static VideoListFragment instance;

    @BindView(R.id.rv_video_list)
    RecyclerView listView;

    VideoListAdapter mAdapter;

    public VideoListFragment() {
        // Required empty public constructor
    }

    public static VideoListFragment newInstance(String message) {
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

        ButterKnife.bind(this, view);
        //listView = (RecyclerView)view.findViewById(R.id.rv_video_list);
        mAdapter = new VideoListAdapter();
        listView.setAdapter(mAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(manager);

        initData();

        return view;
    }

    private void initData() {
        for(int i = 0; i < 20; i++){
            VideoList videoList = new VideoList();
            videoList.setThumbnail(ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher));
            videoList.setTitle("title " + i);
            videoList.setDate("2016. 4. 24");
            videoList.setHit(123);
            videoList.setFavorite(4123);
            mAdapter.add(videoList);
        }
        /*String item = "item 1";
        mAdapter.add(item);*/
    }


}

