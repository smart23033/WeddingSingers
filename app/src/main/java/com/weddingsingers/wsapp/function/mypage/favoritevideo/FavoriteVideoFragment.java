package com.weddingsingers.wsapp.function.mypage.favoritevideo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.VideoList;
import com.weddingsingers.wsapp.function.video.video.VideoActivity;
import com.weddingsingers.wsapp.main.home.VideoListAdapter;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.FavoriteVideoListRequest;
import com.weddingsingers.wsapp.request.VideoListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteVideoFragment extends Fragment {

    @BindView(R.id.favorite_video_rl_list)
    RecyclerView recyclerView;

    VideoListAdapter videoListAdapter;

    public FavoriteVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite_video, container, false);

        ButterKnife.bind(this, view);

        videoListAdapter = new VideoListAdapter();
        videoListAdapter.setOnAdapterItemClickListener(new VideoListAdapter.OnAdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, VideoList videoList, int position) {
                Intent intent = new Intent(getContext(), VideoActivity.class);
                intent.putExtra("videoId", videoList.getVideoId());
                startActivity(intent);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        initData();

        return view;
    }

    private void initData() {

        recyclerView.setAdapter(videoListAdapter);
        videoListAdapter.clear();

        FavoriteVideoListRequest request = new FavoriteVideoListRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<VideoList>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<VideoList>>> request, NetworkResult<List<VideoList>> result) {
//                    여기에 어댑터에 들어갈 놈들이 쌓여야 한다.
                for (int i = 0; i < result.getResult().size(); i++) {
                    VideoList videoList = new VideoList();
                    videoList.setThumbnail(result.getResult().get(i).getThumbnail());
                    videoList.setTitle(result.getResult().get(i).getTitle());
                    videoList.setDate(result.getResult().get(i).getDate());
                    videoList.setHit(result.getResult().get(i).getHit());
                    videoList.setFavorite(result.getResult().get(i).getFavorite());
                    videoListAdapter.add(videoList);
                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<VideoList>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
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
