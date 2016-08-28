package com.weddingsingers.wsapp.function.search.search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentSearchFragment extends Fragment {
    public RecentSearchFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.search_tv_recent)
    TextView recentView;

    @BindView(R.id.search_btn_recent)
    Button recentBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recent_search, container, false);

        ButterKnife.bind(this,view);

        return view;
    }

}