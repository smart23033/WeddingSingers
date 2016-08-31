package com.weddingsingers.wsapp.main.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.search.search.SearchActivity;
import com.weddingsingers.wsapp.main.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    TabLayout tabs;
    MainPagerAdapter mAdapter;
    ViewPager pager;
    CarouselView carouselView;

    int[] bannerImages = {R.drawable.banner1, R.drawable.banner1, R.drawable.banner1, R.drawable.banner1, R.drawable.banner1};

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

        carouselView = (CarouselView) view.findViewById(R.id.main_cv_carousel);
        carouselView.setPageCount(bannerImages.length);
        carouselView.setIndicatorMarginVertical(50);
        carouselView.setIndicatorMarginHorizontal(100);
        carouselView.setIndicatorGravity(Gravity.BOTTOM | Gravity.RIGHT);

        carouselView.setImageListener(imageListener);

        pager = (ViewPager)view.findViewById(R.id.main_pager);
        tabs = (TabLayout)view.findViewById(R.id.main_tabs);

        tabs.addTab(tabs.newTab().setText("TAB1").setTag("tab1"));
        tabs.addTab(tabs.newTab().setText("TAB2").setTag("tab2"));
        tabs.addTab(tabs.newTab().setText("TAB3").setTag("tab3"));

        mAdapter = new MainPagerAdapter(getChildFragmentManager() ,tabs.getTabCount());

        pager.setAdapter(mAdapter);
        tabs.setupWithViewPager(pager);

        return view;
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
