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
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Video;
import com.weddingsingers.wsapp.data.view.ProfileView;
import com.weddingsingers.wsapp.function.video.reservation.ReservationActivity;
import com.weddingsingers.wsapp.function.video.singerinfo.SingerInfoActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.VideoRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {
    final static String KEY_VIDEO_ID = "VideoId";

    @BindView(R.id.video_tv_title)
    TextView titleView;

    @BindView(R.id.video_tv_date)
    TextView dateView;

    @BindView(R.id.video_tv_num_of_favorite)
    TextView favoriteView;

    @BindView(R.id.video_tv_standard_price)
    TextView standardView;

    @BindView(R.id.video_tv_special_price)
    TextView specialView;

    @BindView(R.id.video_rb_rating)
    RatingBar ratingBar;

    @BindView(R.id.video_tv_num_of_person)
    TextView reviewView;

    @BindView(R.id.video_tv_hit)
    TextView hitView;

    ProfileView singerProfileView;

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

        initData();

        return  view;

    }

    private void initData(){
        VideoRequest request = new VideoRequest(getContext(),videoId);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Video>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Video>> request, NetworkResult<Video> result) {
                Toast.makeText(getContext(), "VideoFragment Success", Toast.LENGTH_SHORT).show();

                Video video = new Video();

                video.setDate(result.getResult().getDate());
                video.setSingerName(result.getResult().getSingerName());
                video.setId(result.getResult().getId());
                video.setFavorite(result.getResult().getFavorite());
                video.setComment(result.getResult().getComment());
                video.setHit(result.getResult().getHit());
                video.setRating(result.getResult().getRating());
                video.setReview(result.getResult().getReview());
                video.setStandard(result.getResult().getStandard());
                video.setSpecial(result.getResult().getSpecial());
                video.setTitle(result.getResult().getTitle());
                video.setUrl(result.getResult().getUrl());

                titleView.setText(video.getTitle());
                dateView.setText(video.getDate());
                favoriteView.setText("" + video.getFavorite());
                hitView.setText("" + video.getHit());
                ratingBar.setRating(video.getRating());
                reviewView.setText("" + video.getReview());
                standardView.setText("" + video.getStandard());
                specialView.setText("" + video.getSpecial());


            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Video>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "VideoFragment failure", Toast.LENGTH_SHORT).show();

            }
        });
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
