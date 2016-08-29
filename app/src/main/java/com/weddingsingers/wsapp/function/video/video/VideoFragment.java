package com.weddingsingers.wsapp.function.video.video;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.media.session.MediaControllerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.function.search.search.RecentSearchFragment;
import com.weddingsingers.wsapp.function.video.reservation.ReservationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {


    public VideoFragment() {
        // Required empty public constructor

    }

    @BindView(R.id.video_vv_video)
    VideoView videoView;

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
    void onReserveBtnClicked(){
        moveReservationActivity();
    }

    void moveReservationActivity(){
        startActivity(new Intent(getContext(), ReservationActivity.class));
        getActivity().finish();
    }
}
