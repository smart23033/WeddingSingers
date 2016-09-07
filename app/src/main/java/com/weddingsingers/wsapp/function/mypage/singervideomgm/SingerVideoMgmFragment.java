package com.weddingsingers.wsapp.function.mypage.singervideomgm;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.SingerVideoMgmRequest;

import java.util.ArrayList;
import java.util.List;

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
                videoList.setSelected(true);
                Toast.makeText(getActivity(), "person : " + position + "-" + videoList.getTitle(), Toast.LENGTH_SHORT).show();
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

/*        for(int i = 0; i < 20; i++){
            VideoList videoList = new VideoList();
            videoList.setTitle("video title " + i);
            videoList.setDate("2016. 4. 24");
            videoList.setHit(123);
            videoList.setFavorite(4123);
            videoList.setSelected(false);
            items.add(videoList);
        }*/
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
                videoAddFragment.show(fm, "fragment_dialog_test");
                return true;
            }
            case R.id.video_add_delete: {

                StringBuilder stringBuilder = new StringBuilder();
                for (VideoList vItem : items) {
                    if (vItem.isSelected()) {
                        if (stringBuilder.length() > 0)
                            stringBuilder.append(", ");
                        stringBuilder.append(vItem.getTitle());
                    }
                }
                Toast.makeText(getActivity(), stringBuilder.toString(), Toast.LENGTH_LONG).show();
                int cnt = 0;
                for (VideoList vItem : items) {
                    if (vItem.isSelected()) {
                        cnt++;
                    }
                }

                Toast.makeText(getActivity(), "count : " + cnt, Toast.LENGTH_SHORT).show();

                return true;
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