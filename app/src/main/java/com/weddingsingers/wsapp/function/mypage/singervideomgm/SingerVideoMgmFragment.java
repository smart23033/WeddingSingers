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
import com.weddingsingers.wsapp.data.SingerVideoMgm;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerVideoMgmFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";

    ArrayList<SingerVideoMgm> items = new ArrayList<SingerVideoMgm>();

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

        for(int i = 0; i < 20; i++){
            SingerVideoMgm singerVideoMgm = new SingerVideoMgm();
            singerVideoMgm.setTitle("video title " + i);
            singerVideoMgm.setDate("2016. 4. 24");
            singerVideoMgm.setHit(123);
            singerVideoMgm.setFavorite(4123);
            singerVideoMgm.setSelected(false);
            items.add(singerVideoMgm);

//            mAdapter.add(singerVideoMgm);
        }

        mAdapter = new SingerVideoMgmAdapter(this.items);
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        //initData();

        return view;
    }

    private void initData() {
        for(int i = 0; i < 20; i++){
            SingerVideoMgm singerVideoMgm = new SingerVideoMgm();
            singerVideoMgm.setTitle("video title " + i);
            singerVideoMgm.setDate("2016. 4. 24");
            singerVideoMgm.setHit(123);
            singerVideoMgm.setFavorite(4123);
            singerVideoMgm.setSelected(false);
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
                FragmentManager fm = getFragmentManager();
                VideoAddFragment videoAddFragment = new VideoAddFragment();
                videoAddFragment.show(fm, "fragment_dialog_test");
                return true;
            }
            case R.id.video_add_delete: {

                StringBuilder stringBuilder = new StringBuilder();
                for (SingerVideoMgm vItem : items) {
                    if (vItem.isSelected()) {
                        if (stringBuilder.length() > 0)
                            stringBuilder.append(", ");
                        stringBuilder.append(vItem.getTitle());
                    }
                }
                Toast.makeText(getActivity(), stringBuilder.toString(), Toast.LENGTH_LONG).show();


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
