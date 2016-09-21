package com.weddingsingers.wsapp.function.mypage.singervideomgm;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.SingerVideoMgm;
import com.weddingsingers.wsapp.data.VideoList;
import com.weddingsingers.wsapp.function.video.video.VideoActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.SingerProfileSettingRequest;
import com.weddingsingers.wsapp.request.SingerVideoMgmRequest;
import com.weddingsingers.wsapp.request.VideoDeleteRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerVideoMgmFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";

    ArrayList<VideoList> items = new ArrayList<VideoList>();

    @BindView(R.id.singer_video_mgm_rv_list)
    RecyclerView recyclerView;

    SingerVideoMgmAdapter mAdapter;

    public SingerVideoMgmFragment() {
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

        View view = inflater.inflate(R.layout.fragment_singer_video_mgm, container, false);

        ButterKnife.bind(this, view);

        mAdapter = new SingerVideoMgmAdapter();
        mAdapter.setOnAdapterItemClickListener(new SingerVideoMgmAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, VideoList videoList, int position) {

                Intent intent = new Intent(getActivity(), VideoActivity.class);
                intent.putExtra(VideoActivity.EXTRA_SINGER_ID, videoList.getSingerId());
                intent.putExtra(VideoActivity.EXTRA_VIDEO_ID, videoList.getVideoId());
                startActivity(intent);

            }
        });
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        initData();

        return view;
    }

    private void initData() {

        SingerVideoMgmRequest request = new SingerVideoMgmRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<VideoList>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<VideoList>>> request, NetworkResult<List<VideoList>> result) {
//                    여기에 어댑터에 들어갈 놈들이 쌓여야 한다.
                for (int i = 0; i < result.getResult().size(); i++) {
                    VideoList videoList = new VideoList();
                    videoList.setVideoId(result.getResult().get(i).getVideoId());
                    videoList.setSingerId(result.getResult().get(i).getSingerId());
                    videoList.setThumbnail(result.getResult().get(i).getThumbnail());
                    videoList.setTitle(result.getResult().get(i).getTitle());
                    videoList.setDate(result.getResult().get(i).getDate());
                    videoList.setHit(result.getResult().get(i).getHit());
                    videoList.setFavorite(result.getResult().get(i).getFavorite());
                    videoList.setSelected(false);
                    mAdapter.add(videoList);
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
            case R.id.video_add_add: {
                FragmentManager fm = getFragmentManager();
                VideoAddFragment videoAddFragment = new VideoAddFragment();
                videoAddFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        mAdapter.clear();
                        initData();
                    }
                });
                videoAddFragment.show(fm, "fragment_dialog");
                return true;
            }
            case R.id.video_add_delete: {

                String data = "";
                List<VideoList> videoList = mAdapter.items;

                ArrayList<Integer> deleteList = new ArrayList<Integer>();

                for (int i = 0; i < videoList.size(); i++) {
                    if (videoList.get(i).isSelected()) {
                        deleteList.add(videoList.get(i).getVideoId());
                    }
                }

                if (deleteList.size() <= 0) {
                    Toast.makeText(getActivity(), "삭제할 동영상을 선택하세요", Toast.LENGTH_SHORT).show();
                    return false;
                } else {

                    VideoDeleteRequest request = new VideoDeleteRequest(getContext(), deleteList);
                    NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<String>>() {
                        @Override
                        public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {

//                            Toast.makeText(getActivity(), "success code : " + result.getCode() + " - " + result.getResult(), Toast.LENGTH_SHORT).show();
                            mAdapter.clear();
                            initData();

                        }

                        @Override
                        public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                            Toast.makeText(getActivity(), "VideoDeleteRequest fail - " + errorMessage, Toast.LENGTH_SHORT).show();

                        }
                    });
                    return true;
                }
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.video_add_menu, menu);
    }

}