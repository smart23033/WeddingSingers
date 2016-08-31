package com.weddingsingers.wsapp.function.mypage.singervideomgm;


import android.content.Intent;
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

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SingerVideoMgm;
import com.weddingsingers.wsapp.login.ClauseDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerVideoMgmFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";

    @BindView(R.id.singer_video_mgm_rv_list)
    RecyclerView recyclerView;

    SingerVideoMgmAdapter mAdapter;

    public SingerVideoMgmFragment() {
        // Required empty public constructor
    }

    public static SingerVideoMgmFragment newInstance(String message) {
        SingerVideoMgmFragment fragment = new SingerVideoMgmFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
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
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        initData();

        return view;
    }

    private void initData() {
        for(int i = 0; i < 20; i++){
            SingerVideoMgm singerVideoMgm = new SingerVideoMgm();
            singerVideoMgm.setTitle("video title " + i);
            singerVideoMgm.setDate("2016. 4. 24");
            singerVideoMgm.setHit(123);
            singerVideoMgm.setFavorite(4123);
            mAdapter.add(singerVideoMgm);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                getActivity().finish();
                return true;
            }
            case R.id.video_add_add: {
//                getContext().startActivity(new Intent(getActivity(), VideoAddActivity.class));

                FragmentManager fm = getFragmentManager();
                VideoAddFragment videoAddFragment = new VideoAddFragment();
                videoAddFragment.show(fm, "fragment_dialog_test");
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
