package com.weddingsingers.wsapp.main.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
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
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.EventList;
import com.weddingsingers.wsapp.data.VideoList;
import com.weddingsingers.wsapp.function.event.event.EventActivity;
import com.weddingsingers.wsapp.function.search.search.SearchActivity;
import com.weddingsingers.wsapp.function.video.video.VideoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    @BindView(R.id.main_cv_carousel)
    CarouselView carouselView;

    @BindView(R.id.main_tabHost)
    TabHost tabHost;

    @BindView(R.id.main_rv_list)
    RecyclerView recyclerView;

    VideoListAdapter videoListAdapter;
    EventListAdapter eventListAdapter;

    int[] bannerImages = {R.drawable.main_banner, R.drawable.main_banner, R.drawable.main_banner, R.drawable.main_banner, R.drawable.main_banner};

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

        ButterKnife.bind(this,view);

        carouselView.setPageCount(bannerImages.length);
        carouselView.setIndicatorMarginVertical(50);
        carouselView.setIndicatorMarginHorizontal(100);
        carouselView.setIndicatorGravity(Gravity.BOTTOM | Gravity.RIGHT);
        carouselView.setImageListener(imageListener);

        videoListAdapter = new VideoListAdapter();
        eventListAdapter = new EventListAdapter();

        TabHost.TabContentFactory dummyFactory = new DummyContentFactory(getContext());

        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("TAB1").setContent(dummyFactory));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("TAB2").setContent(dummyFactory));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("TAB3").setContent(dummyFactory));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(!tabHost.getCurrentTabTag().equals(tabId)){
                    tabHost.setCurrentTabByTag(tabId);
                }
                initData(tabId);
            }
        });

        videoListAdapter.setOnAdapterItemClickListener(new VideoListAdapter.OnAdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, VideoList videoList, int position) {
                Intent intent = new Intent(getContext(), VideoActivity.class);
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

        initData("tab1");

        return view;
    }

    private void initData(String tabId) {

        if (tabId != "tab3"){
            recyclerView.setAdapter(videoListAdapter);
            videoListAdapter.clear();
            for(int i = 0; i < 20; i++){
                VideoList videoList = new VideoList();
//            videoList.setThumbnail(ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher));
                videoList.setTitle(tabId + "'s video title " + i);
                videoList.setDate("2016. 4. 24");
                videoList.setHit(123);
                videoList.setFavorite(4123);
                videoListAdapter.add(videoList);
            }
        }else{
            recyclerView.setAdapter(eventListAdapter);
            videoListAdapter.clear();
            eventListAdapter.clear();
            for(int i = 0; i < 20; i++){
                EventList eventList = new EventList();
                //eventList.setThumbnail(ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher));
                eventList.setTitle("Event title " + i);
                eventList.setDate("2016. 4. 24");
                eventListAdapter.add(eventList);
            }
        }

    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(bannerImages[position]);
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.main_menu_search) {
            startActivity(new Intent(getContext(), SearchActivity.class));
            return true;
            }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu,menu);
    }
}
