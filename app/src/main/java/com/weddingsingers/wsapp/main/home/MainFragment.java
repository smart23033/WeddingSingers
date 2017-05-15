package com.weddingsingers.wsapp.main.home;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.EventList;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.VideoList;
import com.weddingsingers.wsapp.function.event.event.EventActivity;
import com.weddingsingers.wsapp.function.search.search.SearchActivity;
import com.weddingsingers.wsapp.function.video.video.VideoActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.VideoListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    final static int TYPE_POPULAR = 1;
    final static int TYPE_LATEST = 2;

    final static String VIDEO_POPULAR = "PopularVideoList";
    final static String VIDEO_LATEST = "LatestVideoList";
    final static String EVENT_LIST = "EventList";


    @BindView(R.id.main_cv_carousel)
    CarouselView carouselView;

    @BindView(R.id.main_tabHost)
    TabHost tabHost;

    @BindView(R.id.main_rv_list)
    RecyclerView recyclerView;

    VideoListAdapter videoListAdapter;
    EventListAdapter eventListAdapter;

    int[] bannerImages = {R.drawable.main_banner, R.drawable.main_banner, R.drawable.main_banner, R.drawable.main_banner};

    public MainFragment() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, view);

        carouselView.setPageCount(bannerImages.length);
        carouselView.setIndicatorMarginVertical(50);
        carouselView.setIndicatorMarginHorizontal(100);
        carouselView.setIndicatorGravity(Gravity.BOTTOM | Gravity.RIGHT);
        carouselView.setImageListener(imageListener);

        videoListAdapter = new VideoListAdapter();
        eventListAdapter = new EventListAdapter();

        TabHost.TabContentFactory dummyFactory = new DummyContentFactory(getContext());

        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec(VIDEO_POPULAR).setIndicator(getString(R.string.main_tab_popular)).setContent(dummyFactory));
        tabHost.addTab(tabHost.newTabSpec(VIDEO_LATEST).setIndicator(getString(R.string.main_tab_new)).setContent(dummyFactory));
        tabHost.addTab(tabHost.newTabSpec(EVENT_LIST).setIndicator(getString(R.string.main_tab_event)).setContent(dummyFactory));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (!tabHost.getCurrentTabTag().equals(tabId)) {
                    tabHost.setCurrentTabByTag(tabId);
                }
                initData(tabId);
            }
        });

        videoListAdapter.setOnAdapterItemClickListener(new VideoListAdapter.OnAdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, VideoList videoList, int position) {
                Intent intent = new Intent(getContext(), VideoActivity.class);

                intent.putExtra(VideoActivity.EXTRA_SINGER_ID, videoList.getSingerId());
                intent.putExtra(VideoActivity.EXTRA_VIDEO_ID, videoList.getVideoId());

                startActivity(intent);
            }
        });


        eventListAdapter.setOnAdapterItemClickListener(new EventListAdapter.OnAdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, EventList eventList, int position) {
                Intent intent = new Intent(getContext(), EventActivity.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData(tabHost.getCurrentTabTag());
    }

    private void initData(String tabId) {
        int type = 0;
        switch (tabId) {
            case VIDEO_POPULAR:
            case VIDEO_LATEST: {

                recyclerView.setAdapter(videoListAdapter);
                videoListAdapter.clear();

                if (tabId == VIDEO_POPULAR) {
                    type = TYPE_POPULAR;
                } else if (tabId == VIDEO_LATEST) {
                    type = TYPE_LATEST;
                }

                VideoListRequest request = new VideoListRequest(getContext(), type);
                getVideoList(request);
                return;

            }

            case EVENT_LIST: {
                recyclerView.setAdapter(eventListAdapter);
                videoListAdapter.clear();
                eventListAdapter.clear();
                for (int i = 0; i < 20; i++) {
                    EventList eventList = new EventList();
//                    eventList.setThumbnail(ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher));
                    eventList.setTitle("Event title " + i);
                    eventList.setDate("2016. 4. 24");
                    eventListAdapter.add(eventList);
                }
                return;
            }
        }

    }

    private void getVideoList(VideoListRequest request) {
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
                    videoList.setSingerId(result.getResult().get(i).getSingerId());
                    videoList.setVideoId(result.getResult().get(i).getVideoId());
                    videoListAdapter.add(videoList);
                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<VideoList>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(bannerImages[position]);
            //imageView.setImageURI(Uri.parse(bannerImages[position]));
        }
    };



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.main_menu_search) {
            startActivity(new Intent(getContext(), SearchActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

}
