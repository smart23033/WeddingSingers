package com.weddingsingers.wsapp.function.video.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.video.reservation.ReservationActivity;
import com.weddingsingers.wsapp.function.video.singerinfo.SingerInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {
    final static String KEY_VIDEO_ID = "VideoId";

    public VideoFragment() {
        // Required empty public constructor

    }

    @BindView(R.id.video_vv_video)
    VideoView videoView;


    public static VideoFragment newInstance(int videoId) {
        VideoFragment fragment = new VideoFragment();
        Bundle b = new Bundle();
        b.putInt(KEY_VIDEO_ID,videoId);
        fragment.setArguments(b);
        return fragment;
    }

    int videoId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            videoId = getArguments().getInt(KEY_VIDEO_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_video, container, false);

        ButterKnife.bind(this,view);

        videoView.setVideoPath("https://www.youtube.com/watch?v=zQhZUGNR6l4");
        final MediaController mediaController = new MediaController(getContext());
        videoView.setMediaController(mediaController);

        return  view;

    }

    @OnClick(R.id.view_profile_btn_reserve)
    void onReserveBtnClick(){
        moveReservationActivity();
    }

    @OnClick(R.id.view_profile_rl_profile)
    void onProfileClick(){
        startActivity(new Intent(getActivity(), SingerInfoActivity.class));
    }

    void moveReservationActivity(){
        startActivity(new Intent(getContext(), ReservationActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                getActivity().finish();
                return true;
            }
            case R.id.video_menu_favorite: {
                if(!item.isChecked()){
                    item.setChecked(true);
                    item.setIcon(R.drawable.search_ic_favorite_on);
                }else{
                    item.setChecked(false);
                    item.setIcon(R.drawable.search_ic_favorite_off);
                }
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.video_menu,menu);
    }
}
