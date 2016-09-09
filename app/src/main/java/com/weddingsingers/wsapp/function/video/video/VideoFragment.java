package com.weddingsingers.wsapp.function.video.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Rating;
import com.weddingsingers.wsapp.data.Singer;
import com.weddingsingers.wsapp.data.Video;
import com.weddingsingers.wsapp.data.view.ProfileView;
import com.weddingsingers.wsapp.function.video.reservation.ReservationActivity;
import com.weddingsingers.wsapp.function.video.singerinfo.SingerInfoActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.CancelFavoriteRequest;
import com.weddingsingers.wsapp.request.FavoriteRequest;
import com.weddingsingers.wsapp.request.RatingRequest;
import com.weddingsingers.wsapp.request.SingerProfileRequest;
import com.weddingsingers.wsapp.request.VideoRequest;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {
    final static String KEY_VIDEO_ID = "videoId";
    final static String KEY_SINGER_ID = "singerId";

    final static int ARG_SIMPLE = 1;
    final static int ARG_RATING = 1;

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

    @BindView(R.id.video_pv_profile)
    ProfileView singerProfileView;

    public VideoFragment() {
        // Required empty public constructor

    }

    @BindView(R.id.video_vv_video)
    VideoView videoView;


    public static VideoFragment newInstance(int videoId,int singerId) {
        VideoFragment fragment = new VideoFragment();
        Bundle b = new Bundle();
        b.putInt(KEY_VIDEO_ID, videoId);
        b.putInt(KEY_SINGER_ID, singerId);
        fragment.setArguments(b);
        return fragment;
    }

    int videoId;
    int singerId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            videoId = getArguments().getInt(KEY_VIDEO_ID);
            singerId = getArguments().getInt(KEY_SINGER_ID);
            Log.i("VideoFragment","onCreate에서 singerId : " + singerId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        ButterKnife.bind(this, view);

        videoView.setVideoPath("https://www.youtube.com/watch?v=zQhZUGNR6l4");
        final MediaController mediaController = new MediaController(getContext());
        videoView.setMediaController(mediaController);

        initData();

        return view;

    }


    int isFavorite;

    private void initData() {
        VideoRequest videoRequest = new VideoRequest(getContext(), videoId);
        NetworkManager.getInstance().getNetworkData(videoRequest, new NetworkManager.OnResultListener<NetworkResult<Video>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Video>> request, NetworkResult<Video> result) {
                Video video = new Video();

                video.setDate(result.getResult().getDate());
                video.setId(result.getResult().getId());
                video.setFavorite(result.getResult().getFavorite());
                video.setHit(result.getResult().getHit());
                video.setTitle(result.getResult().getTitle());
                video.setUrl(result.getResult().getUrl());
                isFavorite = result.getResult().getIsFavorite();

                titleView.setText(video.getTitle());
                dateView.setText(video.getDate());
                favoriteView.setText("" + video.getFavorite());
                hitView.setText("" + video.getHit());

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Video>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "videoReqeust fail", Toast.LENGTH_SHORT).show();
            }
        });

        Log.i("VideoFragment","SingerProifleRequest 앞에 singerId : " + singerId);
        SingerProfileRequest singerProfileRequest = new SingerProfileRequest(getContext(), singerId, ARG_SIMPLE);
        NetworkManager.getInstance().getNetworkData(singerProfileRequest, new NetworkManager.OnResultListener<NetworkResult<Singer>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Singer>> request, NetworkResult<Singer> result) {
                // 가격에 , 찍기
                NumberFormat nf = NumberFormat.getInstance();

                String singerName = result.getResult().getSingerName();
                String singerImage = result.getResult().getSingerImage();
                String comment = result.getResult().getComment();
                int standard = result.getResult().getStandard();
                int special = result.getResult().getSpecial();

                singerProfileView.setComment(comment);
                singerProfileView.setSingerName(singerName);
                singerProfileView.setSingerImage(singerImage);

                standardView.setText(nf.format(standard));
                specialView.setText(nf.format(special));

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Singer>> request, int errorCode, String errorMessage, Throwable e) {
                Log.i("VideoFragment", "SingerProfileRequest Simple Fail : " + errorMessage);
            }
        });

        Log.i("VideoFragment","RatingRequest 앞에 singerId : " + singerId);
        RatingRequest ratingRequest = new RatingRequest(getContext(), singerId, ARG_RATING);
        NetworkManager.getInstance().getNetworkData(ratingRequest, new NetworkManager.OnResultListener<NetworkResult<Rating>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Rating>> request, NetworkResult<Rating> result) {
                Log.i("VideoFragment", "RatingRequest Simple success");
                int reviewCnt = result.getResult().getReviewCnt();
                float reviewPoint = result.getResult().getReviewPoint();

                reviewView.setText("" + reviewCnt);
                ratingBar.setRating(reviewPoint);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Rating>> request, int errorCode, String errorMessage, Throwable e) {
                Log.i("VideoFragment", "RatingRequest Simple Fail : " + errorMessage);
            }
        });

    }

    @OnClick(R.id.view_profile_btn_reserve)
    void onReserveBtnClick() {
        Intent intent = new Intent(new Intent(getContext(), ReservationActivity.class));
        intent.putExtra(ReservationActivity.EXTRA_SINGER_ID, singerId);
        startActivity(intent);
    }

    @OnClick(R.id.view_profile_rl_profile)
    void onProfileClick() {
        startActivity(new Intent(getActivity(), SingerInfoActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                getActivity().finish();
                return true;
            }
            case R.id.video_menu_favorite: {
                if (!item.isChecked()) {
                    Log.i("VideoFragment","FavoriteRequest 앞에 videoId : " + videoId);
                    FavoriteRequest favoriteRequest = new FavoriteRequest(getContext(), videoId);
                    NetworkManager.getInstance().getNetworkData(favoriteRequest, new NetworkManager.OnResultListener<NetworkResult<String>>() {
                        @Override
                        public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                            Toast.makeText(getContext(), "I like this", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                            Toast.makeText(getContext(), "favoriteRequest fail : " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });

                    item.setChecked(true);
                    item.setIcon(R.drawable.search_ic_favorite_on);
                } else {

                    CancelFavoriteRequest favoriteRequest = new CancelFavoriteRequest(getContext(), videoId);
                    NetworkManager.getInstance().getNetworkData(favoriteRequest, new NetworkManager.OnResultListener<NetworkResult<String>>() {
                        @Override
                        public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                            Toast.makeText(getContext(), "cancel favorite", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                            Toast.makeText(getContext(), "cancelFavoriteRequest fail : " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });

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
        inflater.inflate(R.menu.video_menu, menu);

        MenuItem item = menu.findItem(R.id.video_menu_favorite);

        if (isFavorite == 1) {
            item.setChecked(true);
        } else {
            item.setChecked(false);
        }
    }
}